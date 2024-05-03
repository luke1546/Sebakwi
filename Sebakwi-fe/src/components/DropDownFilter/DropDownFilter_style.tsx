import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const DropDownWrapper = styled.div`
  width: 240px;
  height: 26px;
  display: flex;
`;

export const DropDownLabel = styled.label`
  margin-right: 14px;
  color: ${PALETTE.MAIN_BLUE};
  font-size: 17px;
  font-family: ${Noto_Sans_KR.bold.variable};
  font-weight: ${Noto_Sans_KR.bold.weight};
`;

export const DropDownSelect = styled.select`
  width: 134px;
  height: 26px;
  background-color: white;
  border-radius: 3px;
  border: 1px solid ${PALETTE.MAIN_BLUE};
  color: ${PALETTE.MAIN_BLACK};
  font-size: 15px;
  font-family: ${Noto_Sans_KR.medium.variable};
  font-weight: ${Noto_Sans_KR.medium.weight};
`;

export const DropDownOption = styled.option`
  color: ${PALETTE.MAIN_BLACK};
  font-size: 15px;
  font-family: ${Noto_Sans_KR.medium.variable};
  font-weight: ${Noto_Sans_KR.medium.weight};
`;
