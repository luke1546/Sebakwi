import styled from 'styled-components';
import { PALETTE } from 'styles';

export const ButtonWrapper = styled.button`
  border: 0px;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: ${PALETTE.MAIN_BLUE};
  color: ${PALETTE.MAIN_BLACK};
  padding: 0px 8px;
  cursor: pointer;
`;
