import React, { useCallback, useEffect, useState } from 'react';
import CameraConnect from './CameraConnectSection/CameraConnectSection';
import AbnormalStatus from './AbnormalStatusSection/AbnormalStatusSection';
import ReplaceSection from './ReplaceSection/ReplaceSection';
import MonitoringChart from './MonitoringChartSection/MonitoringChartSection';
import OHTState from './OHTStateSection/OHTStateSection';
import { GoAlert } from 'react-icons/go';
import { toast, Slide } from 'react-toastify';
import axios from 'axios';
import * as Comp from 'components';
import { WheelData } from 'types';
import * as Styled from './DashBoard_style';

// 토스트 알림 - 전역변수 선언
let shownAlerts = new Set();

export default function DashBoradPage() {
  // sse를 통해 새로운 데이터가 왔음을 알리는 상태
  const [receiveData, setReceiveData] = useState(false);

  // 첫 이상 데이터 받아오기.
  const [abData, setAbData] = useState<WheelData | null>(null);

  const baseUrl = process.env.REACT_APP_BASE_URL;

  const fetchData = useCallback(async () => {
    try {
      const response = await axios.get<WheelData>(`${baseUrl}/wheels/monthly`);
      setAbData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }, [baseUrl]);


  useEffect(() => {
    fetchData();
  }, [fetchData, receiveData]);

  useEffect(() => {
    //SSE 연결
    const baseUrl = process.env.REACT_APP_BASE_URL;
    const eventSource = new EventSource(`${baseUrl}/wheels/monthly/sse`);

    eventSource.addEventListener('sse', (event) => {
      const newMessage: WheelData = JSON.parse(event.data);
      if (typeof newMessage == 'string') console.log(newMessage);
      else {
        console.log('데이터 : ' + event.data);
        // 새로운 데이터 도착을 알리기
        setReceiveData((prevState) => !prevState);

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
    <Styled.DashboardContainer>
      <Styled.CardContainer>
        <Comp.Card title="카메라 연결 현황" width="600px" height="275px">
          <CameraConnect />
        </Comp.Card>
        <Comp.Card title="금월 이상 현황" width="400px" height="275px">
          <AbnormalStatus data={abData} />
        </Comp.Card>
        <Styled.SideCard>
          <Comp.Card title="교체 주기 도래 휠" width="310px" height="120px" padding="10px">
            <ReplaceSection />
          </Comp.Card>
          <Comp.Card title="OHT 현황" width="20rem" height="5.5rem" padding="0.7rem">
            <OHTState />
          </Comp.Card>
        </Styled.SideCard>
      </Styled.CardContainer>
      <Styled.CardContainer>
        <Comp.Card title="모니터링 차트" width="1440px" height="160px">
          <MonitoringChart shouldRefetch={receiveData} />
        </Comp.Card>
      </Styled.CardContainer>
      <Styled.AlarmContainer
        position="bottom-right"
        autoClose={4000}
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
    </Styled.DashboardContainer>
  );
}
