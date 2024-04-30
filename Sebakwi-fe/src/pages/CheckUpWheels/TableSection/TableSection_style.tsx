import styled from 'styled-components';
import { PALETTE } from 'styles';

export const AttributesRow = styled.tr`
  background-color: ${PALETTE.SUB_BLUE};
`;

export const AttributesTitle = styled.th`
  color: ${PALETTE.MAIN_BLACK};
`;

export const AttributesValue = styled.td`
  color: ${PALETTE.MAIN_BLACK};
  background-color: ${PALETTE.LIGHT_BLUE};
  text-align: center;
`;
