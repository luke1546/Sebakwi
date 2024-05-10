import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const AbContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  gap: 30px;
`;

export const AbTop = styled.div`
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
`;

export const AbBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const AbBoxTitle = styled.div`
  color: ${PALETTE.MAIN_BLACK};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 1rem;
`;

export const AbBoxCount = styled.div`
  color: ${PALETTE.MAIN_BLACK};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 3rem;
`;

// 표
export const TableContainer = styled.div`
  max-height: 140px;
  overflow: auto;
`;

export const Table = styled.table`
  border: 1px solid ${PALETTE.LIGHT_BLACK};
  border-collapse: collapse;
  width: 100%;
  max-height: 140px;
`;

export const TableHead = styled.thead`
  position: sticky;
  top: 0px;
  margin: 0;
  z-index: 1;
`;

export const TableBody = styled.tbody`
  /* display: block; */
  max-height: 140px;
  overflow-y: scroll;
  
`;

export const TableTr = styled.tr`
  background-color: ${PALETTE.SUB_BLUE};
`;

export const TableTh = styled.th`
  font-weight: ${Noto_Sans_KR.medium.weight};
  color: ${PALETTE.MAIN_BLACK};
  text-align: center;
  border: 1px solid ${PALETTE.LIGHT_BLACK};
`;

export const TableTd = styled.td`
  font-weight: ${Noto_Sans_KR.regular.weight};
  color: ${PALETTE.MAIN_BLACK};
  height: 25px;
  font-size: 0.85rem;
  text-align: center;
  border: 1px solid ${PALETTE.LIGHT_BLACK};
`;
