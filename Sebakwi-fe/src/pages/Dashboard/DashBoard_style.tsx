import { ToastContainer } from 'react-toastify';
import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const DashboardContainer = styled.div`
  /* height: calc(100vh - 74px); */
  height: 90vh;
  display: flex;
  flex-direction: column;
  background-color: ${PALETTE.LIGHT_BLUE};
  /* padding: 0px 30px; */
  padding: 0px 2vw;
`;


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


export const AlarmContainer = styled(ToastContainer)`
  .Toastify__toast {
    font-family: ${Noto_Sans_KR.semiBold.variable};
    font-weight: ${Noto_Sans_KR.semiBold.weight};
    background: #e74c3c;
  }
  .Toastify__toast-body {
    font-family: ${Noto_Sans_KR.medium.variable};
    font-weight: ${Noto_Sans_KR.medium.weight};
    color: white;
  }

  .Toastify__toast-icon {
    margin-right: 10px;
    color: white;
    font-size: 30px;
  }
  .Toastify__close-button {
    color: white;
  }
`;