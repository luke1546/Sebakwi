import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Modal = styled.div`
  background-color: white;
  border-radius: 5px;
  width: 75%;
  height: 60%;
  min-height: 250px;
  /* max-height: 6000px; */
  /* width: 1200px;
  height: 450px; */
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
  padding: 20px 20px;
  color: white;
`;

export const TitleInfo = styled.div`
  display: grid;
  grid-template-columns: auto auto auto auto auto auto auto auto auto;
  max-width: 840px;
`;

export const Result = styled.span<{ status?: string }>`
color: ${({ status }) => {
    switch (status) {
      case 'ABNORMAL':
        return 'red';
      case 'NORMAL':
        return `white`;
      default:
        return 'gray';
    }
  }};

`

export const Content = styled.div`
  display: grid;
  grid-template-columns: 2fr 3fr 3fr;
  height: 85%;
  box-sizing: border-box;
  padding: 30px 0;
`;
export const SubContent = styled.div`
  display: grid;
  grid-template-rows: 12% calc(88% - 60px);
  padding: 0 30px;
`;

export const SubTitle = styled.div`
  background-color: ${PALETTE.LIGHT_BLUE};
  text-align: center;
  color: ${PALETTE.MAIN_BLUE};
  font-weight: bold;
  padding: 8px;
`;

export const Image = styled.img`
  width: 100%;
  height: 250px;
`;

export const ResultTable = styled.div`
  table {
    border-collapse: collapse;
    height: 100%;
  }
  td,
  th {
    border: none; /* 테두리 없음 */
    width: 200px;
    height: 25%;
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


export const CheckBoxInput = styled.input`
  appearance: none;
  border: 2px solid ${PALETTE.MAIN_BLACK};
  border-radius: 0.25rem;
  width: 1.5rem;
  height: 1.5rem;
  margin-right: 0.5rem;

  &:checked {
    border-color: transparent;
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='white' xmlns='http://www.w3.org/2000/svg'%3e%3cpath d='M5.707 7.293a1 1 0 0 0-1.414 1.414l2 2a1 1 0 0 0 1.414 0l4-4a1 1 0 0 0-1.414-1.414L7 8.586 5.707 7.293z'/%3e%3c/svg%3e");
    background-size: 120% 120%;
    background-position: 50%;
    background-repeat: no-repeat;
    background-color: ${PALETTE.MAIN_BLUE};
  }
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
