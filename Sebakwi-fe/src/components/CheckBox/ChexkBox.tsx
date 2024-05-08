import * as Styled from './CheckBox_style';
import { CheckBoxProps } from 'types';

export default function CheckBox(props: CheckBoxProps) {
  const { name, checked, onChange } = props;
  return (
    <Styled.CheckBoxWrapper>
      <Styled.CheckBoxInput
        type="checkbox"
        checked={checked === 1 ? true : false}
        onChange={onChange}
      />
      <Styled.CheckBoxTextWrapper>
        <Styled.CheckBoxText>{name}</Styled.CheckBoxText>
      </Styled.CheckBoxTextWrapper>
    </Styled.CheckBoxWrapper>
  );
}
