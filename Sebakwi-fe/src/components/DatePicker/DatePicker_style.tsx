import ReactDatePicker from 'react-datepicker';
import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

interface DatePickerProps {
  disabled?: boolean;
}

export const StyledDatePickerwWrapper = styled.section`
  .react-datepicker__input-container {
    width: 134px;
    height: 26px;
  }

  .react-datepicker__header {
    background-color: ${PALETTE.SUB_BLUE};
    color: ${PALETTE.MAIN_BLACK};
    font-weight: ${Noto_Sans_KR.bold.weight};
    font-family: 'Noto Sans KR', sans-serif;
  }

  .react-datepicker__day-name {
    color: ${PALETTE.MAIN_BLACK};
    font-weight: ${Noto_Sans_KR.bold.weight};
    font-family: 'Noto Sans KR', sans-serif;
  }
  .react-datepicker__month {
    color: ${PALETTE.MAIN_BLACK};
    font-weight: ${Noto_Sans_KR.bold.weight};
    font-family: 'Noto Sans KR', sans-serif;
  }

  .react-datepicker-popper {
    margin-top: 5px;
  }
`;

export const StyledDatePicker = styled(ReactDatePicker)`
  background-color: white;
  color: black;
  border: 1px solid ${PALETTE.MAIN_BLUE};
  border-radius: 3px;
  padding: 8px;
  font-size: 15px;
  text-align: center;
`;

export const DatePickerContainer = styled.div<DatePickerProps>`
  width: 136px;
  height: 26px;
  border: 1.5px solid ${PALETTE.MAIN_BLACK};
  border-radius: 5px;
  padding: 0px 10px;
  opacity: ${props => props.disabled ? 0.5 : 1};
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const DatePickerInputWrapper = styled.div<DatePickerProps>`
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
