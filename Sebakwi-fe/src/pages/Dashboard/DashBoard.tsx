import * as styled from './DashBoard_style';
import Card from '../../components/Card/Card';
import CameraConnect from './CameraConnectSection/CameraConnect';
import AbnormalStatus from './AbnormalStatusSection/AbnormalStatus';
import ReplaceSection from './ReplaceSection/ReplaceSection';
import MonitoringChart from './MonitoringChartSection/MonitoringChart';
import OHTState from './OHTStateSection/OHTState';
import { useEffect, useState } from 'react';

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

const initialState: WheelData = {
  count: {
    crack: 0,
    stamp: 0,
    peeling: 0,
    total: 0
  },
  wheelList: [
    {
      wheelNumber: "SM00017",
      ohtNumber: "VM0005 ",
      position: 1,
      crack: false,
      stamp: false,
      peeling: false
    }
  ]
};


const SSEComponent: React.FC = () => {
  const [messages, setMessages] = useState<WheelData>();
  useEffect(() => {
    const baseUrl = process.env.REACT_APP_BASE_URL;
    const eventSource = new EventSource(`${baseUrl}/wheels/monthly/1`);

    eventSource.addEventListener('sse', (event) => {
      const newMessage: WheelData = JSON.parse(event.data);
      setMessages(newMessage);
      if (typeof newMessage == 'string') console.log(newMessage)
      else {
        console.log("dd.d." + event.data)
        console.log(`messages : ${messages}`);
        console.log(`newMessage : ${newMessage.count.crack}`);
      }
    });

    console.log("안녕하세요 : " + messages);


    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div></div>
  );
};



export default function DashBoradPage() {
  return (
    <styled.DashboardContainer>
      <styled.CardContainer>
        <Card title="카메라 연결 현황" width="600px" height="275px">
          <CameraConnect></CameraConnect>
        </Card>
        <Card title="금월 이상 현황" width="400px" height="275px">
          <AbnormalStatus></AbnormalStatus>
        </Card>
        <styled.SideCard>
          <Card title="교체 주기 도래 휠" width="310px" height="120px" padding="10px">
            <ReplaceSection></ReplaceSection>
          </Card>
          <Card title="OHT 현황" width="310px" height="90px" padding="10px">
            {/* <OHTState></OHTState> */}
            <SSEComponent></SSEComponent>
          </Card>
        </styled.SideCard>
      </styled.CardContainer>
      <styled.CardContainer>
        <Card title="모니터링 차트" width="1440px" height="160px">
          <MonitoringChart></MonitoringChart>
        </Card>
      </styled.CardContainer>
    </styled.DashboardContainer>
  );
}
