import styled from 'styled-components';
import { PALETTE } from '../../styles/colors';
import Noto_Sans_KR from 'styles/fonts';

export const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  > h1 {
    font-family: ${Noto_Sans_KR.extraLight};
    color: ${PALETTE.LIGHT_BLACK};
  }
`;
