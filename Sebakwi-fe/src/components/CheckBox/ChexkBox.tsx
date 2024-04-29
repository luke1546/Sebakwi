import * as Styled from './CheckBox_style';
import { CheckBoxProps } from 'types';

export default function CheckBox(props: CheckBoxProps) {
  const { name, id, checked, onChange } = props;
  return (
    <Styled.CheckBoxWrapper>
      <Styled.CheckBoxInput
        type="checkbox"
        id={name}
        checked={checked}
        onChange={() => onChange(id)}
      ></Styled.CheckBoxInput>
      <Styled.CheckBoxLabel htmlFor={name}>{name}</Styled.CheckBoxLabel>
    </Styled.CheckBoxWrapper>
  );
}
