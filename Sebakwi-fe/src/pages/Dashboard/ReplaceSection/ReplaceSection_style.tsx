import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const ReplaceContainer = styled.div`
  max-height: 100px;
  overflow: auto;
`;

export const ReplaceWrapper = styled.div`
  display: flex;
  border-bottom: 1px solid ${PALETTE.LIGHT_BLACK};
  padding: 5px 0px;
  justify-content: space-around;
`;

export const ReplaceHeader = styled.div`
  display: flex;
  padding-bottom: 5px;
  justify-content: space-around;
  background-color: ${PALETTE.SUB_BLUE};
  position: sticky;
  top: 0px;
  margin: 0;
`;

export const WheelNumber = styled.div`
  font-weight: ${Noto_Sans_KR.regular.weight};
  font-size: 0.9rem;
`;

export const Date = styled.div`
  font-weight: ${Noto_Sans_KR.regular.weight};
  font-size: 0.9rem;
`;
