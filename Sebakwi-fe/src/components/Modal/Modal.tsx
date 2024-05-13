import React, { useEffect, useState } from 'react';
import { ModalProps, CheckupDataProps, TableData } from 'types';
import ModalTable from './Sub/ModalTable';
import axios from 'axios';
import * as Styled from './Modal_style';
import Wheel3D from 'components/Wheel3D/Wheel3D';

export default function Modal(props: ModalProps) {
  const initialData = [
    {
      checkupListId: 0,
      wheelNumber: "",
      position: 0,
      ohtNumber: "",
      checkedDate: "",
      wheelImage: "",
      diameter: 0,
      crack: false,
      stamp: false,
      peeling: false,
      status: "",
      createdDate: "",
    },
    {
      checkupListId: 0,
      wheelNumber: "",
      position: 0,
      ohtNumber: "",
      checkedDate: "",
      wheelImage: "",
      diameter: 0,
      crack: false,
      stamp: false,
      peeling: false,
      status: "",
      createdDate: "",
    },
    {
      checkupListId: 0,
      wheelNumber: "",
      position: 0,
      ohtNumber: "",
      checkedDate: "",
      wheelImage: "",
      diameter: 0,
      crack: false,
      stamp: false,
      peeling: false,
      status: "",
      createdDate: "",
    },
    {
      checkupListId: 0,
      wheelNumber: "",
      position: 0,
      ohtNumber: "",
      checkedDate: "",
      wheelImage: "",
      diameter: 0,
      crack: false,
      stamp: false,
      peeling: false,
      status: "",
      createdDate: "",
    },
    {
      checkupListId: 0,
      wheelNumber: "",
      position: 0,
      ohtNumber: "",
      checkedDate: "",
      wheelImage: "",
      diameter: 0,
      crack: false,
      stamp: false,
      peeling: false,
      status: "",
      createdDate: "",
    },
  ]
  const { id, onClose } = props;
  const [data, setData] = useState<CheckupDataProps[]>(initialData);
  const [selected, setSelected] = useState<number>(4);

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
  }, [onClose]);

  // 콜백 함수: 자식 컴포넌트에서 데이터를 받음
  const handleDataFromChild = (prop: number) => {
    // console.log('Received data from child:', data);
    if (data[prop].position == 0) alert("데이터가 없습니다.");
    else setSelected(prop);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const baseUrl = process.env.REACT_APP_BASE_URL;
        const response = await axios.get<CheckupDataProps[]>(`${baseUrl}/checkup_list/${id}`);
        // console.log(response.data);
        let updatedData = initialData;
        response.data.forEach((e, index) => {
          if (data === null) return; // 데이터가 null이면 아무 작업도 수행하지 않음
          updatedData[e.position - 1] = e;
          if (e.checkupListId === id) {
            setSelected(e.position - 1);
          }
        })
        console.log(updatedData);
        setData(updatedData);
      } catch (error) {
        console.error('Error fetching data: ', error);
      }
    };

    fetchData();
  }, [id]);

  const tableData: TableData[] = data
    ?
    [
      { item: '마모도', value: data[selected].diameter },
      { item: '찍힘', value: data[selected].stamp },
      { item: '크랙', value: data[selected].crack },
      { item: '박리', value: data[selected].peeling },
    ]
    : [];

  return (
    <Styled.ModalWrapper onClick={onClose}>
      <Styled.Modal onClick={(event) => event.stopPropagation()}>
        <Styled.Title>
          <Styled.TitleInfo>
            <div>검진 ID : {data[selected].wheelNumber}</div>
            <div> | </div>
            <div>검진 일자 : {data[selected].checkedDate}</div>
            <div> | </div>
            <div>교체 일자 : {data[selected].createdDate} </div>
            <div>|</div>
            <div>
              위치 :
              {data[selected].position === 1
                ? 'FL'
                : data[selected].position === 2
                  ? 'FR'
                  : data[selected].position === 3
                    ? 'RL'
                    : data[selected].position === 4
                      ? 'RR'
                      : ''}{' '}
            </div>
            <div>|</div>
            <div>
              검진 결과 :{' '}
              <Styled.Result status={data[selected].status}>
                {' '}
                {data[selected].status === 'ABNORMAL' ? '비정상' : '정상'}{' '}
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
              selected={data[selected].position}
              OHTId={data[selected].ohtNumber}
              status={data}
              sendDataToParent={handleDataFromChild}
            />
          </Styled.SubContent>
          <Styled.SubContent>
            <Styled.SubTitle>
              <div>휠 상세 이미지</div>
              <div>
                <Styled.DetailImg src={data ? data[selected].wheelImage : ''} />
              </div>
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
