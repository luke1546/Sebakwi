import React from 'react';
import * as Styled from './DropDownFilter_style';

interface DropDownFilterProps {
  title: string;
  options: { value: string; label: string }[];
}

export default function DropDownFilter(props: DropDownFilterProps) {
  const { title, options } = props;
  return (
    <Styled.DropDownWrapper>
      <Styled.DropDownLabel>{title}</Styled.DropDownLabel>
      <Styled.DropDownSelect>
        {options.map((option) => (
          <Styled.DropDownOption key={option.value} value={option.value}>
            {option.label}
          </Styled.DropDownOption>
        ))}
      </Styled.DropDownSelect>
    </Styled.DropDownWrapper>
  );
}
