import styled from 'styled-components';
import { PALETTE } from 'styles';

export const DropDownWrapper = styled.div`
  width: 134px;
  height: 26px;
  display: flex;
`;
export const DropDownSelect = styled.select`
  width: 134px;
  height: 26px;
  background-color: white;
  border-radius: 3px;
  border: 1px solid ${PALETTE.MAIN_BLUE};
  padding: 0px 3px 0px 3px;
  color: ${PALETTE.MAIN_BLACK};
  font-size: 15px;
  font-weight: 600;
`;

export const DropDownOption = styled.option`
  color: ${PALETTE.MAIN_BLACK};
  font-size: 15px;
  font-weight: 600;
`;
