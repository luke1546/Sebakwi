import styled from 'styled-components';
import { PALETTE } from '../../styles/colors/color';

export const MainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  > h1 {
    color: ${PALETTE.LIGHT_BLACK};
  }
`;
