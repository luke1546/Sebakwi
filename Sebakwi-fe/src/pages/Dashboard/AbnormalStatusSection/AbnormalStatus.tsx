import React from 'react';
import * as Styled from './AbnormalStatus_style';

// api 받아오기
const data = [
  {
    wheelNumber: 'SM0013',
    ohtNumber: 'VM0005',
    position: 0,
    status: ['크랙', '찍힘', '박리'],
  },
  {
    wheelNumber: 'SM0011',
    ohtNumber: 'VM0004',
    position: 2,
    status: ['크랙'],
  },
  {
    wheelNumber: 'SM0011',
    ohtNumber: 'VM0004',
    position: 2,
    status: ['크랙'],
  },
  {
    wheelNumber: 'SM0011',
    ohtNumber: 'VM0004',
    position: 2,
    status: ['크랙'],
  },
  {
    wheelNumber: 'SM0011',
    ohtNumber: 'VM0004',
    position: 2,
    status: ['크랙'],
  },
  //   {
  //     wheelNumber: 'SM0011',
  //     ohtNumber: 'VM0004',
  //     position: 2,
  //     status: ['크랙'],
  //   },
];

type Abnormal = {
  title: string;
  count: number;
};

interface WheelData {
  wheelNumber: string;
  ohtNumber: string;
  position: number;
  status: string[];
}

interface TableProps {
  data: WheelData[];
}

// 비정상 수 컴포넌트
function AbnormalDetail({ title, count }: Abnormal) {
  return (
    <Styled.AbBox>
      <Styled.AbBoxTitle>{title}</Styled.AbBoxTitle>
      <Styled.AbBoxCount>{count}</Styled.AbBoxCount>
    </Styled.AbBox>
  );
}

// 표
function WheelTable({ data }: TableProps) {
  return (
    <Styled.TableContainer>
      <Styled.Table>
        <Styled.TableHead>
          <Styled.TableTr>
            <Styled.TableTh>휠 번호</Styled.TableTh>
            <Styled.TableTh>OHT 번호</Styled.TableTh>
            <Styled.TableTh>위치</Styled.TableTh>
            <Styled.TableTh>상태</Styled.TableTh>
          </Styled.TableTr>
        </Styled.TableHead>
        <Styled.TableBody>
          {data.map((item, index) => (
            <tr key={index}>
              <Styled.TableTd>{item.wheelNumber}</Styled.TableTd>
              <Styled.TableTd>{item.ohtNumber}</Styled.TableTd>
              <Styled.TableTd>{item.position}</Styled.TableTd>
              <Styled.TableTd>{item.status.join(', ')}</Styled.TableTd>
            </tr>
          ))}
        </Styled.TableBody>
      </Styled.Table>
    </Styled.TableContainer>
  );
}

export default function AbnormalStatus() {
  return (
    <Styled.AbContainer>
      <Styled.AbTop>
        <AbnormalDetail title="찍힘" count={0}></AbnormalDetail>
        <AbnormalDetail title="크랙" count={1}></AbnormalDetail>
        <AbnormalDetail title="박리" count={2}></AbnormalDetail>
        <AbnormalDetail title="합계" count={3}></AbnormalDetail>
      </Styled.AbTop>
      <WheelTable data={data} />
    </Styled.AbContainer>
  );
}
