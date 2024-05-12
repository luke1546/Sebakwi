import React, { useState } from 'react';
import * as styled from './DashBoard_style';
import Card from '../../components/Card/Card';
import CameraConnect from './CameraConnectSection/CameraConnectSection';
import AbnormalStatus from './AbnormalStatusSection/AbnormalStatusSection';
import ReplaceSection from './ReplaceSection/ReplaceSection';
import MonitoringChart from './MonitoringChartSection/MonitoringChartSection';
import OHTState from './OHTStateSection/OHTStateSection';

export default function DashBoradPage() {
  // sse를 통해 새로운 데이터가 왔음을 알리는 상태
  const [receiveData, setReceiveData] = useState(false);

  function handleReceiveData() {
    setReceiveData((prevState) => !prevState);
  }

  return (
    <styled.DashboardContainer>
      <styled.CardContainer>
        <Card title="카메라 연결 현황" width="600px" height="275px">
          <CameraConnect />
        </Card>
        <Card title="금월 이상 현황" width="400px" height="275px">
          <AbnormalStatus onRefetch={handleReceiveData} />
        </Card>
        <styled.SideCard>
          <Card title="교체 주기 도래 휠" width="310px" height="120px" padding="10px">
            <ReplaceSection></ReplaceSection>
          </Card>
          <Card title="OHT 현황" width="310px" height="90px" padding="10px">
            <OHTState />
          </Card>
        </styled.SideCard>
      </styled.CardContainer>
      <styled.CardContainer>
        <Card title="모니터링 차트" width="1440px" height="160px">
          <MonitoringChart shouldRefetch={receiveData} />
        </Card>
      </styled.CardContainer>
    </styled.DashboardContainer>
  );
}
