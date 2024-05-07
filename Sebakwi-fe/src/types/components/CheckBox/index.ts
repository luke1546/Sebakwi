export interface CheckBoxProps {
    name: string;
    checked: number;
    onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  }