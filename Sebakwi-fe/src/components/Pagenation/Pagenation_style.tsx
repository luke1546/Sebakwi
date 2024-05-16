import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const PagenationWrapper = styled.div`
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
`;

export const Button = styled.button`
  background-color: white;
  color: black;
  border: none;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  transition: background-color 0.3s, color 0.3s;
  font-weight: ${Noto_Sans_KR.medium.weight};

  &:hover {
    background-color: ${PALETTE.LIGHT_BLUE};
  }

  &.selected {
    background-color: ${PALETTE.MAIN_BLUE};
    color: white;
  }
`;
