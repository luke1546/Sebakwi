export interface CheckBoxProps {
    name: string;
    id: number;
    checked: boolean;
    onChange: (id : number) => void;
  }