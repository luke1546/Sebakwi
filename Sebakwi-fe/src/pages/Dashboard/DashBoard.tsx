import * as styled from './DashBoard_style';
import Card from '../../components/Card/Card';

export default function DashBoradPage() {
  return (
    <styled.DashboardContainer>
      <Card title="카메라 연결 현황" width="500px" height="500px">
        <div>This is the content of Card 2.dfadf</div>
      </Card>
    </styled.DashboardContainer>
  );
}
