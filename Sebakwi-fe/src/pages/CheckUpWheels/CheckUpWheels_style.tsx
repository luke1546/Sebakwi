import styled from 'styled-components';
import { PALETTE } from 'styles';

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: calc(100% - 72px);
  padding: 40px 60px 0px 60px;
  background-color: ${PALETTE.LIGHT_BLUE};
`;
