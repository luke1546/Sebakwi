import React, { useEffect, useState } from 'react';
import { ModalProps, CheckupDataProps, TableData, ModalData, SerialNumbers } from 'types';
import ModalTable from './Sub/ModalTable';
import axios from 'axios';
import * as Styled from './Modal_style';
import Wheel3D from 'components/Wheel3D/Wheel3D';

export default function Modal(props: ModalProps) {
  const { id, onClose } = props;
  const [data, setData] = useState<CheckupDataProps | null>(null);
  const [serialNumbers, setSerialNumbers] = useState<SerialNumbers | null>(null);

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

  // 콜백 함수: 자식 컴포넌트에서 데이터를 받음
  const handleDataFromChild = (data: CheckupDataProps) => {
    console.log('Received data from child:', data);
    setData(data);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const baseUrl = process.env.REACT_APP_BASE_URL;
        const response = await axios.get<ModalData>(`${baseUrl}/checkup_list/${id}`);
        setData(response.data.checkupListDetailModalWheel); // 응답 데이터를 state에 저장
        setSerialNumbers(response.data.checkupListDetailModalWheelNumberList);
      } catch (error) {
        console.error('Error fetching data: ', error);
      }
    };

    fetchData();
  }, [id]);

  const tableData: TableData[] = data
    ? [
      { item: '마모도', value: data.diameter },
      { item: '찍힘', value: data.stamp },
      { item: '크랙', value: data.crack },
      { item: '박리', value: data.peeling },
    ]
    : [];

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
            <div>
              위치 :
              {data?.position === 1
                ? 'LF'
                : data?.position === 2
                  ? 'RF'
                  : data?.position === 3
                    ? 'LR'
                    : data?.position === 4
                      ? 'RR'
                      : ''}{' '}
            </div>
            <div>|</div>
            <div>
              검진 결과 :{' '}
              <Styled.Result status={data?.status}>
                {' '}
                {data?.status === 'ABNORMAL' ? '비정상' : '정상'}{' '}
              </Styled.Result>
            </div>
          </Styled.TitleInfo>
          <Styled.ESCButton onClick={onClose}>X</Styled.ESCButton>
        </Styled.Title>
        <Styled.Content>
          <Styled.SubContent>
            <Styled.SubTitle>
              <div>휠 위치</div>
            </Styled.SubTitle>
            <Wheel3D
              position={data?.position}
              OHTId={data?.ohtNumber}
              sendDataToParent={handleDataFromChild}
              serialNumbers={serialNumbers}
            />
          </Styled.SubContent>
          <Styled.SubContent>
            <Styled.SubTitle>
              <div>휠 상세 이미지</div>
            </Styled.SubTitle>
          </Styled.SubContent>
          <Styled.SubContent>
            <Styled.SubTitle>
              <div>검진 결과</div>
            </Styled.SubTitle>
            <Styled.ResultTable>
              <ModalTable data={tableData} />
            </Styled.ResultTable>
          </Styled.SubContent>
        </Styled.Content>
      </Styled.Modal>
    </Styled.ModalWrapper>
  );
}
