import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Modal = styled.div`
  background-color: white;
  border-radius: 5px;
  /* width: 75%;
  height: 60%; */
  width: 1200px;
  height: 450px;
`;

export const ModalWrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.div`
  display: grid;
  grid-template-columns: 20fr 1fr;
  background-color: ${PALETTE.MAIN_BLUE};
  padding: 15px 15px;
  color: white;
  align-items: center;
`;

export const TitleInfo = styled.div`
  display: grid;
  grid-template-columns: auto auto auto auto auto auto auto;
  max-width: 700px;
`;

export const Result = styled.span<{ status?: string }>`
color: ${({ status }) => {
    switch (status) {
      case 'ABNORMAL':
        return 'red';
      case 'NORMAL':
        return '${PALETTE.MAIN_BLUE}';
      default:
        return 'gray';
    }
  }};

`

export const Container = styled.div`
  display: grid;
  grid-template-rows: 1fr 12fr;
`;

export const Content = styled.div`
  display: grid;
  grid-template-columns: 2fr 3fr 3fr;
`;
export const SubContent = styled.div`
  display: grid;
  grid-template-rows: 1fr 15fr;
  padding: 30px;
`;

export const SubTitle = styled.div`
  background-color: ${PALETTE.LIGHT_BLUE};
  text-align: center;
  color: ${PALETTE.MAIN_BLUE};
  font-family: ${Noto_Sans_KR.extraBold.variable};
  padding: 8px;
`;

export const Image = styled.img`
  width: 100%;
  height: 250px;
`;

export const ResultTable = styled.div`
  table {
    border-collapse: collapse;
  }
  td,
  th {
    border: none; /* 테두리 없음 */
    width: 200px;
    height: 60px;
    text-align: center;
    /* padding: 15px 50px; */
  }
  td {
    border-bottom: 1px solid lightgray; /* 하단 경계선 */
    border-right: 1px solid lightgray;
  }
  tr:last-child > td {
    border-bottom: none;
  }
  td:last-child {
    border-right: none;
  }
`;

export const CheckboxWrapper = styled.label`
  display: inline-block;
  position: relative;
  padding-left: 25px;
  margin-right: 15px;
  cursor: pointer;
  user-select: none;
`;

export const CheckboxInput = styled.input`
  position: absolute;
  opacity: 0;
  cursor: pointer;
`;

export const CheckboxCheckmark = styled.span`
  position: absolute;
  top: 0;
  left: 0;
  height: 15px;
  width: 15px;
  background-color: #fff;
  border: 1px solid #000;
`;

export const ESCButton = styled.button`
    border: none;
    background-color: ${PALETTE.MAIN_BLUE};
    color: white;
    &:hover{cursor: pointer;
    }
`
