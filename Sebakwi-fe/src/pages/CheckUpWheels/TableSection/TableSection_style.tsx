import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

const TableLineColor = '#9B9B9B';

interface AttributesTitleProps {
  width: number;
}

export const Wrapper = styled.div`
  height: calc(600px - 72px);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

export const AttributesRow = styled.tr`
  height: 40px;
  background-color: ${PALETTE.SUB_BLUE};
  border-top: 1px solid ${TableLineColor};
  border-bottom: 1px solid ${TableLineColor};
`;

export const TableTuple = styled.tr`
  border-top: 1px solid ${TableLineColor};
  border-bottom: 1px solid ${TableLineColor};
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
`;

export const AttributesTitle = styled.th<AttributesTitleProps>`
  font-size: 16px;
  width: ${(props) => props.width}px;
  color: ${PALETTE.MAIN_BLUE};
  font-weight: ${Noto_Sans_KR.bold.weight};
  text-align: center;
  border-right: 1px solid ${TableLineColor};
  &:last-child {
    border-right: none;
  }
`;

export const AttributesValue = styled.td`
  height: 30px;
  color: ${PALETTE.MAIN_BLACK};
  text-align: center;
  border-right: 1px solid ${TableLineColor};
  font-size: 15px;
  font-weight: ${Noto_Sans_KR.medium.weight};
  &:last-child {
    border-right: none;
  }
`;
