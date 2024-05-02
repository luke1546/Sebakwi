import { BaseButtonProps } from 'types';
import * as Styled from './BaseButton_style';

export default function BaseButton(props: BaseButtonProps) {
  const { children, type, onClick, className } = props;
  return (
    <Styled.ButtonWrapper type={type} onClick={onClick} className={className}>
      {children}
    </Styled.ButtonWrapper>
  );
}

BaseButton.Styled = Styled.ButtonWrapper;
