export interface DatePickerProps {
  selectedDate: Date | null; // 선택된 날짜
  startDate?: Date | null; // 시작 날짜
  onChange: (date: Date | null) => void; // 날짜 변경 핸들러
  disabled?: boolean; // 비활성화 여부
}

export interface DateInputProps {
  value?: string;
  onClick?: () => void;
}
