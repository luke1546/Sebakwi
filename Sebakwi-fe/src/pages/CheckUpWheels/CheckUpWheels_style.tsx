import styled from 'styled-components';
import { ToastContainer } from 'react-toastify';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Wrapper = styled.div`
  height: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: start;
  padding: 40px 60px 40px 50px;
  background-color: ${PALETTE.LIGHT_BLUE};
`;

export const FilterSectionWrapper = styled.div`
  margin-bottom: 35px;
`;


export const AlarmContainer = styled(ToastContainer)`
  .Toastify__toast {
    font-family: ${Noto_Sans_KR.semiBold.variable};
    font-weight: ${Noto_Sans_KR.semiBold.weight};
    background: #e74c3c;
  }
  .Toastify__toast-body {
    /* font-family: ${Noto_Sans_KR.semiBold.variable};
    font-weight: ${Noto_Sans_KR.semiBold.weight}; */
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
