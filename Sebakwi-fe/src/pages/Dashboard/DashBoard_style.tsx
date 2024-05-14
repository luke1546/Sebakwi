import styled from 'styled-components';
import { PALETTE } from 'styles';

export const DashboardContainer = styled.div`
  /* height: calc(100vh - 74px); */
  height: 90vh;
  display: flex;
  flex-direction: column;
  background-color: ${PALETTE.LIGHT_BLUE};
  /* padding: 0px 30px; */
  padding: 0px 2vw;
`;

// export const DashboardContainer = styled.div`
//   height: 90vh;
//   display: grid;
//   grid-template-columns: repeat(12, 1fr); /* 12열 격자 */
//   grid-template-rows: repeat(3, 1fr); /* 3행 격자 */
//   grid-template-areas:
//     'cam cam cam cam ab ab ab ch ch ch '
//     'cam cam cam cam ab ab ab oht oht oht '
//     'chart chart chart chart chart chart chart chart chart chart chart chart';
//   background-color: ${PALETTE.LIGHT_BLUE};
//   padding: 0px 2vw;
//   gap: 1rem; /* 격자 간격 */
// `;

export const CardContainer = styled.div`
  width: 100vh;
  display: flex;
  /* justify-content: space-between; */

  /* padding-top: 23px; */
  padding-top: 3vh;
`;

export const SideCard = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
