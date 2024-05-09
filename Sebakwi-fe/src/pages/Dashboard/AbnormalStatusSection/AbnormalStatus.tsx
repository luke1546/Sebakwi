import { useEffect, useState } from 'react';
import * as Styled from './AbnormalStatus_style';
import axios from 'axios';

interface Abnormal {
  title: string;
  count: number | undefined;
};

interface TableProps {
  data: Wheel[] | undefined;
}

interface Wheel {
  wheelNumber: string;
  ohtNumber: string;
  position: number;
  crack: boolean;
  stamp: boolean;
  peeling: boolean;
}

// Count 데이터 타입 정의
interface Count {
  crack: number;
  stamp: number;
  peeling: number;
  total: number;
}

// 전체 데이터를 포함하는 인터페이스
interface WheelData {
  count: Count;
  wheelList: Wheel[];
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
          {data ? data.map((item, index) => (
            <tr key={index}>
              <Styled.TableTd>{item.wheelNumber}</Styled.TableTd>
              <Styled.TableTd>{item.ohtNumber}</Styled.TableTd>
              <Styled.TableTd>{item.position}</Styled.TableTd>
              <Styled.TableTd>{item.crack ? "크랙 " : ""} {item.peeling ? "박리 " : ""} {item.stamp ? "찍힘" : ""} </Styled.TableTd>
            </tr>
          )) : <tr></tr>}
        </Styled.TableBody>
      </Styled.Table>
    </Styled.TableContainer>
  );
}

export default function AbnormalStatus() {  // 첫 이상 데이터 받아오기.
  const baseUrl = process.env.REACT_APP_BASE_URL;
  const [abData, setAbData] = useState<WheelData | null>(null);
  const fetchData = async () => {
    try {
      const response = await axios.get<WheelData>(`${baseUrl}/wheels/monthly`);
      setAbData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [])

  useEffect(() => { //SSE 연결
    const eventSource = new EventSource(`${baseUrl}/wheels/monthly/1`);

    eventSource.addEventListener('sse', (event) => {
      const newMessage: WheelData = JSON.parse(event.data);
      if (typeof newMessage == 'string') console.log(newMessage)
      else {
        console.log("데이터 : " + event.data);
        setAbData(newMessage);
      }
    });

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <Styled.AbContainer>
      <Styled.AbTop>
        <AbnormalDetail title="찍힘" count={abData ? abData.count.stamp : 0}></AbnormalDetail>
        <AbnormalDetail title="크랙" count={abData ? abData.count.crack : 0}></AbnormalDetail>
        <AbnormalDetail title="박리" count={abData ? abData.count.peeling : 0}></AbnormalDetail>
        <AbnormalDetail title="합계" count={abData ? abData.count.total : 0}></AbnormalDetail>
      </Styled.AbTop>
      <WheelTable data={abData?.wheelList} />
    </Styled.AbContainer>
  );
}
