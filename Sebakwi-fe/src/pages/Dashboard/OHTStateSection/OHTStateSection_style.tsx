import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const StContainer = styled.div`
  display: flex;
  justify-content: space-around;
  margin-top: 5px;
`;

export const StBoxTitle = styled.div`
  color: ${PALETTE.MAIN_BLACK};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 1rem;
`;
export const StBoxCount = styled.div`
  color: ${PALETTE.MAIN_BLACK};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 1.5rem;
`;

export const StatusBox = styled.div`
  /* display: grid;
  grid-template-columns: 1fr 1fr;
  padding: 3px; */
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 17px;
`;
