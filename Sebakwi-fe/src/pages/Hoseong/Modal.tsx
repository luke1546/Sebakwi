// components/Modal.tsx
import React, { useEffect } from 'react';
import * as Styled from "./Modal_style";
import OHTWheel from './Wheel';

const Modal = ({ onClose }: { onClose: () => void }) => {

    type TableData = {
        item: string;
        value: string | boolean;
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
        { item: '마모도', value: '0.5 mm' },
        { item: '찍힘', value: false }, // 체크박스는 boolean 타입으로
        { item: '크랙', value: false },
        { item: '박리', value: false },
        { item: '교체 일자', value: '2022.04-18' }
    ];

    function renderTable(data: TableData[]) {
        return (
            <table>
                <tbody>
                    {data.map((row, index) => (
                        <tr key={index}>
                            <td>{row.item}</td>
                            <td>{typeof row.value === 'boolean' ? (
                                <input type="checkbox" checked={row.value} readOnly />
                            ) : (
                                row.value
                            )}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        );
    }



    return (
        <Styled.ModalWrapper onClick={onClose}>
            <Styled.Modal onClick={(event) => event.stopPropagation()}>
                <Styled.Container>
                    <Styled.Title>
                        <Styled.TitleInfo>
                            <div>검진 ID : SM0013</div>
                            <div> | </div>
                            <div>검진 일자 : 2024-04-18 11:51:00</div>
                            <div> | </div>
                            <div>위치 : FL </div>
                            <div>|</div>
                            <div>검진 결과 :<Styled.Result> 비정상</Styled.Result></div>
                        </Styled.TitleInfo>
                        <Styled.ESCButton onClick={onClose}>X</Styled.ESCButton>
                    </Styled.Title>
                    <Styled.Content>
                        <Styled.SubContent>
                            <Styled.SubTitle><div>휠 위치</div></Styled.SubTitle>
                            <OHTWheel></OHTWheel>

                        </Styled.SubContent>
                        <Styled.SubContent>
                            <Styled.SubTitle><div>휠 상세 이미지</div></Styled.SubTitle>

                        </Styled.SubContent><Styled.SubContent>
                            <Styled.SubTitle><div>검진 결과</div></Styled.SubTitle>
                            <Styled.ResultTable>{renderTable(tableData)}</Styled.ResultTable>

                        </Styled.SubContent>
                    </Styled.Content>
                </Styled.Container>
            </Styled.Modal>
        </Styled.ModalWrapper >
    );
}

export default Modal;
