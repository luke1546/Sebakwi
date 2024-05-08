import * as styled from './DashBoard_style';
import Card from '../../components/Card/Card';
import CameraConnect from './CameraConnectSection/CameraConnect';
import AbnormalStatus from './AbnormalStatusSection/AbnormalStatus';
import ReplaceSection from './ReplaceSection/ReplaceSection';
import MonitoringChart from './MonitoringChartSection/MonitoringChart';
import OHTState from './OHTStateSection/OHTState';

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
            <OHTState></OHTState>
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
