import { useEffect, useState } from 'react';
import { Abnormal, TableProps, WheelData, AbnormalStatusSectionProps } from 'types';
import * as Styled from './AbnormalStatusSection_style';
import axios from 'axios';
import { Slide, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { GoAlert } from 'react-icons/go';

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
          {data ? (
            data.map((item, index) => {
              // 상태를 배열로 관리
              let statuses = [];
              if (item.stamp) statuses.push('찍힘');
              if (item.crack) statuses.push('크랙');
              if (item.peeling) statuses.push('박리');

              // 상태 배열을 문자열로 합치기
              let statusString = statuses.join(', ');

              return (
                <tr key={index}>
                  <Styled.TableTd>{item.wheelNumber}</Styled.TableTd>
                  <Styled.TableTd>{item.ohtNumber}</Styled.TableTd>
                  <Styled.TableTd>{item.position}</Styled.TableTd>
                  <Styled.TableTd>{statusString}</Styled.TableTd>
                </tr>
              );
            })
          ) : (
            <tr></tr>
          )}
        </Styled.TableBody>
      </Styled.Table>
    </Styled.TableContainer>
  );
}

// 토스트 알림 - 전역변수 선언
let shownAlerts = new Set();

export default function AbnormalStatusSection({ onRefetch }: AbnormalStatusSectionProps) {
  // 첫 이상 데이터 받아오기.
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
  }, []);

  useEffect(() => {
    //SSE 연결
    const eventSource = new EventSource(`${baseUrl}/wheels/monthly/sse`);

    eventSource.addEventListener('sse', (event) => {
      const newMessage: WheelData = JSON.parse(event.data);
      if (typeof newMessage == 'string') console.log(newMessage);
      else {
        console.log('데이터 : ' + event.data);
        setAbData(newMessage);
        // 새로운 데이터 도착을 알리기
        onRefetch();

        // 토스트 알림
        if (newMessage.wheelList.length > 0) {
          const newWheelData = newMessage.wheelList[newMessage.wheelList.length - 1];
          const alertKey = `${newWheelData.wheelNumber} - ${newWheelData.crack ? 'crack' : ''}${
            newWheelData.stamp ? 'stamp' : ''
          }${newWheelData.peeling ? 'peeling' : ''}`;

          if (!shownAlerts.has(alertKey)) {
            shownAlerts.add(alertKey);
            if (newWheelData.crack) {
              toast.error(`${newWheelData.wheelNumber} 휠 크랙 발생`, { icon: <GoAlert /> });
            }
            if (newWheelData.stamp) {
              toast.error(`${newWheelData.wheelNumber} 휠 찍힘 발생`, { icon: <GoAlert /> });
            }
            if (newWheelData.peeling) {
              toast.error(`${newWheelData.wheelNumber} 휠 박리 발생`, { icon: <GoAlert /> });
            }
          }
        }
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
      <Styled.AlarmContainer
        position="bottom-right"
        autoClose={4000}
        // autoClose={false}
        hideProgressBar
        newestOnTop={false}
        closeOnClick={false}
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={false}
        pauseOnHover
        theme="colored"
        transition={Slide}
      />
    </Styled.AbContainer>
  );
}
