// components/Modal.tsx
import React, { useEffect, useState } from 'react';
import * as Styled from "./Modal_style";
import OHTWheel from './Wheel';
import axios from 'axios';


const Modal = ({ onClose, id }: { onClose: () => void, id: number | undefined }) => {
    const [data, setData] = useState<CheckupData | null>(null);

    interface CheckupData {
        checkedDate: string;
        crack: boolean;
        createdDate: string;
        diameter: number;
        ohtNumber: string;
        peeling: boolean;
        position: number;
        stamp: boolean;
        status: string;
        wheelImage: string;
        wheelNumber: string;
    }

    type TableData = {
        item: string | undefined;
        value: string | number | boolean | undefined;
    }

    useEffect(() => {
        // 키보드 입력 이벤트를 감지하는 함수입니다.
        const handleKeyDown = (event: KeyboardEvent) => {
            // ESC 키가 눌렸는지 확인합니다.
            if (event.key === 'Escape' || event.keyCode === 27) {
                onClose();
                // 원하는 동작을 여기서 실행할 수 있습니다.
            }
        };
        // `window` 객체에 이벤트 리스너를 추가합니다.
        window.addEventListener('keydown', handleKeyDown);

        // 컴포넌트가 언마운트될 때 이벤트 리스너를 제거합니다.
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
        };
    }, []);




    const tableData: TableData[] = [
        { item: '마모도', value: data?.diameter },
        { item: '찍힘', value: data?.stamp }, // 체크박스는 boolean 타입으로
        { item: '크랙', value: data?.crack },
        { item: '박리', value: data?.peeling },
    ];

    function renderTable(data: TableData[]) {
        return (
            <table>
                <tbody>
                    {data.map((row, index) => (
                        <tr key={index}>
                            <td>{row.item}</td>
                            <td>{typeof row.value === 'boolean' ? (
                                <Styled.CheckBoxInput type="checkbox" checked={row.value} readOnly></Styled.CheckBoxInput>
                            ) : (
                                row.value
                            )}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    }



    useEffect(() => {
        const fetchData = async () => {
            try {
                const baseUrl = process.env.REACT_APP_BASE_URL;
                const response = await axios.get<CheckupData>(`${baseUrl}/checkup_list/${id}`);
                setData(response.data);  // 응답 데이터를 state에 저장\
            } catch (error) {
                console.error("Error fetching data: ", error);
            }
        };

        fetchData();
    }, []);

    // 콜백 함수: 자식 컴포넌트에서 데이터를 받음
    const handleDataFromChild = (data: CheckupData) => {
        console.log("Received data from child:", data);
        setData(data);
    };

    return (
        <Styled.ModalWrapper onClick={onClose}>
            <Styled.Modal onClick={(event) => event.stopPropagation()}>
                <Styled.Title>
                    <Styled.TitleInfo>
                        <div>검진 ID : {data?.wheelNumber}</div>
                        <div> | </div>
                        <div>검진 일자 : {data?.checkedDate}</div>
                        <div> | </div>
                        <div>교체 일자 : {data?.createdDate} </div>
                        <div>|</div>
                        <div>위치 : {data?.position === 1 ? "FL" : data?.position === 2 ? "FR" : data?.position === 3 ? "BL" : data?.position === 4 ? "BR" : ""} </div>
                        <div>|</div>
                        <div>검진 결과 : <Styled.Result status={data?.status}> {data?.status === "ABNORMAL" ? "비정상" : "정상"} </Styled.Result></div>
                    </Styled.TitleInfo>
                    <Styled.ESCButton onClick={onClose}>X</Styled.ESCButton>
                </Styled.Title>
                <Styled.Content>
                    <Styled.SubContent>
                        <Styled.SubTitle><div>휠 위치</div></Styled.SubTitle>
                        <OHTWheel position={data?.position} OHTId={data?.ohtNumber} sendDataToParent={handleDataFromChild} ></OHTWheel>
                    </Styled.SubContent>
                    <Styled.SubContent>
                        <Styled.SubTitle><div>휠 상세 이미지</div></Styled.SubTitle>

                    </Styled.SubContent><Styled.SubContent>
                        <Styled.SubTitle><div>검진 결과</div></Styled.SubTitle>
                        <Styled.ResultTable>{renderTable(tableData)}</Styled.ResultTable>

                    </Styled.SubContent>
                </Styled.Content>

            </Styled.Modal>
        </Styled.ModalWrapper >
    );
}

export default Modal;
