import React, { forwardRef, useState } from 'react';
import { ko } from 'date-fns/locale';
import 'react-datepicker/dist/react-datepicker.css';
import * as Styled from './DatePicker_style';


interface DatePickerProps {
  selectedDate: Date | null; // 선택된 날짜
  startDate?: Date | null; // 시작 날짜
  onChange: (date: Date | null) => void; // 날짜 변경 핸들러
  disabled?: boolean; // 비활성화 여부
}

interface DateInputProps {
  value?: string;
  onClick?: () => void;
}

export default function DatePicker(props : DatePickerProps) {
  const { selectedDate, startDate, disabled, onChange } = props;

  const CustomInput = forwardRef<HTMLDivElement, DateInputProps>(({ value, onClick }, ref) => (
    <Styled.DatePickerContainer onClick={onClick} ref={ref} disabled={disabled}>
      <Styled.DatePickerInputWrapper disabled={disabled}>
        <p>{value}</p>
      </Styled.DatePickerInputWrapper>
      <img src="/images/calender.png" alt="123" width={14} height={16} />
    </Styled.DatePickerContainer>
  ));

  return (
    <Styled.StyledDatePickerwWrapper>
      <Styled.StyledDatePicker
        dateFormat="yyyy-MM-dd" // 날짜 형태
        shouldCloseOnSelect // 날짜를 선택하면 datepicker가 자동으로 닫힘
        locale={ko} // 한국어로 설정
        minDate={startDate} // minDate 이전 날짜 선택 불가
        maxDate={new Date()} // maxDate 이후 날짜 선택 불가
        selected={selectedDate}
        onChange={onChange}
        customInput={<CustomInput />}
        disabled={disabled}
      />
    </Styled.StyledDatePickerwWrapper>
  );
}
