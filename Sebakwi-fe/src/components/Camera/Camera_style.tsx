import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const CameraContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100px;
`;

export const CameraTitle = styled.div`
  color: ${PALETTE.MAIN_BLACK};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 0.8rem;
  margin-bottom: 10px;
`;

export const CameraImage = styled.div`
  margin-bottom: 5px;
`;

export const ToggleSwitch = styled.label`
  position: relative;
  display: inline-block;
  width: 50px;
  height: 15px;
`;

export const ToggleSlider = styled.span`
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: ${PALETTE.LIGHT_BLACK};
  -webkit-transition: 0.4s;
  transition: 0.4s;
  border-radius: 34px;

  &:before {
    position: absolute;
    content: '';
    height: 8.5px;
    width: 8.5px;
    left: 4px;
    bottom: 3.5px;
    background-color: white;
    -webkit-transition: 0.4s;
    transition: 0.4s;
    border-radius: 50%;
  }
`;

export const CheckBox = styled.input`
  opacity: 0;
  width: 0;
  height: 0;

  &:checked + ${ToggleSlider} {
    background-color: ${PALETTE.MAIN_BLUE};
  }

  &:focus + ${ToggleSlider} {
    box-shadow: 0 0 1px ${PALETTE.MAIN_BLUE};
  }

  &:checked + ${ToggleSlider}:before {
    -webkit-transform: translateX(32px);
    -ms-transform: translateX(32px);
    transform: translateX(32px);
  }
`;
