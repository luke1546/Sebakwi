import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const FilterInputWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-right: 40px;
  white-space: nowrap;
`;

export const FilterInputTitle = styled.p`
  margin: 0px;
  color: ${PALETTE.MAIN_BLUE};
  font-size: 17px;
  font-weight: ${Noto_Sans_KR.bold.weight};
  margin-right: 14px;
`;
