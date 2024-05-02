export interface BaseButtonProps {
  children: React.ReactNode;
  type: 'submit' | 'button' | 'reset';
  onClick?: (...args: any[]) => void;
  className?: string;
}
