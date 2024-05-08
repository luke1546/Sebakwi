import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const CheckBoxWrapper = styled.div`
  display: flex;
  height: 100%;
  align-items: center;
  margin-right: 2rem;
`;

export const CheckBoxInput = styled.input`
  appearance: none;
  border: 2px solid ${PALETTE.MAIN_BLACK};
  border-radius: 0.25rem;
  width: 19px;
  height: 19px;
  margin: 1px 8px 0px 0px;

  &:checked {
    border-color: transparent;
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='white' xmlns='http://www.w3.org/2000/svg'%3e%3cpath d='M5.707 7.293a1 1 0 0 0-1.414 1.414l2 2a1 1 0 0 0 1.414 0l4-4a1 1 0 0 0-1.414-1.414L7 8.586 5.707 7.293z'/%3e%3c/svg%3e");
    background-size: 130% 130%;
    background-position: 50%;
    background-repeat: no-repeat;
    background-color: ${PALETTE.MAIN_BLUE};
  }
`;

export const CheckBoxTextWrapper = styled.div`
  white-space: nowrap;
`;

export const CheckBoxText = styled.text`
  white-space: nowrap;
  font-size: 17px;
  font-weight: ${Noto_Sans_KR.medium.weight};
`;
