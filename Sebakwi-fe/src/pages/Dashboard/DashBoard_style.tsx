import styled from 'styled-components';
import { PALETTE } from 'styles';

export const DashboardContainer = styled.div`
  height: calc(100vh - 74px);
  display: flex;
  flex-direction: column;
  background-color: ${PALETTE.LIGHT_BLUE};
  padding: 0px 30px;
`;

export const CardContainer = styled.div`
  width: 100%;
  display: flex;
  /* justify-content: space-between; */
  padding-top: 23px;
`;

export const SideCard = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
