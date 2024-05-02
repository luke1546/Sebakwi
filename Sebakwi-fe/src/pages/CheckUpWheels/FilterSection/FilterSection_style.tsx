import { BaseButton } from 'components';
import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Wrapper = styled.div`
  width: 100%;
  height: 80px;
  margin-bottom: 50px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const CheckBoxWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: start;
`;

export const SearchWrapper = styled.div`
  height: fit-content;
  display: flex;
  justify-content: space-between;
`;

// Left
export const LeftContentWrapper = styled.div`
  display: flex;
`;

export const DateTimePickerWrapper = styled.div`
  display: flex;
  margin-right: 30px;
`;

export const DatePickerContainer = styled.div`
  width: 136px;
  height: 26px;
  border: 1.5px solid ${PALETTE.MAIN_BLACK};
  border-radius: 5px;
  padding: 0px 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-right: 20px;
`;

export const DatePickerInputWrapper = styled.div`
  width: 100%;
  height: 26px;
  display: flex;
  justify-content: center;
  align-items: center;

  > p {
    margin: 0px;
    color: ${PALETTE.MAIN_BLACK};
    font-size: 16px;
  }
`;

export const TimePickerContainer = styled.div`
  width: 136px;
  height: 26px;
  border: 1.5px solid ${PALETTE.MAIN_BLACK};
  border-radius: 5px;
  padding: 0px 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const TimePickerInputWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

  > p {
    margin: 0px;
    color: ${PALETTE.MAIN_BLACK};
    font-size: 16px;
  }
`;

export const RangeWrapper = styled.div`
  height: fit-content;
  display: flex;
  align-items: center;

  > p {
    margin: 0px 15px;

    color: ${PALETTE.MAIN_BLACK};
    font-size: 20px;
  }
`;

export const ButtonWrapper = styled.div`
  display: flex;
`;

export const ResetButton = styled(BaseButton)`
  width: 115px;
  height: 27.6px;
  border: 1px solid ${PALETTE.MAIN_BLUE};
  background-color: white;
  margin-right: 15px;
`;

export const ResetButtonText = styled.p`
  margin: 0px;
  font-size: 16px;
  font-family: ${Noto_Sans_KR.semiBold.variable};
  font-weight: ${Noto_Sans_KR.semiBold.weight};
  color: ${PALETTE.MAIN_BLUE};
`;

export const SearchButton = styled(BaseButton)`
  width: 115px;
  height: 27.6px;
  color: ${PALETTE.MAIN_BLUE};
`;

export const SearchButtonText = styled.p`
  margin: 0px;
  font-size: 16px;
  font-family: ${Noto_Sans_KR.semiBold.variable};
  font-weight: ${Noto_Sans_KR.semiBold.weight};
  color: white;
`;

// Right
export const RightContentWrapper = styled.div`
  display: flex;
  align-items: center;
`;
