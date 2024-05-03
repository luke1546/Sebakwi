import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  > h1 {
    font-family: ${Noto_Sans_KR.thin.variable};
    font-weight: ${Noto_Sans_KR.thin.weight};
    color: ${PALETTE.LIGHT_BLACK};
  }
`;

