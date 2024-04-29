import * as styled from './DashBoard_style';
import Card from '../../components/Card/Card';

export default function DashBoradPage() {
  return (
    <styled.DashboardContainer>
      <styled.CardContainer>
        <Card title="카메라 연결 현황" width="600px" height="250px">
          <div>This is the content of Card 2.dfadf</div>
        </Card>
        <Card title="금월 이상 현황" width="400px" height="250px">
          <div>This is the content of Card 2.dfadf</div>
        </Card>
        <styled.SideCard>
          <Card title="교체 대상 휠 목록" width="300px" height="70px">
            <div>This is the content of Card 2.dfadf</div>
          </Card>
          <Card title="OHT 현황" width="300px" height="80px">
            <div>This is the content of Card 2.dfadf</div>
          </Card>
        </styled.SideCard>
      </styled.CardContainer>
      <styled.CardContainer>
        <Card title="모니터링 차트" width="1440px" height="160px">
          <div>This is the content of Card 2.dfadf</div>
        </Card>
      </styled.CardContainer>
    </styled.DashboardContainer>
  );
}
