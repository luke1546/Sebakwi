export interface DropDownFilterProps {
  options: { label: string; value: number }[];
  value: number;
  onChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}
