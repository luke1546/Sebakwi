import React from 'react';
import { DropDownFilterProps } from 'types';
import * as Styled from './DropDownFilter_style';

export default function DropDownFilter(props: DropDownFilterProps) {
  const { options, value, onChange } = props;

  return (
    <Styled.DropDownWrapper>
      <Styled.DropDownSelect value={value} onChange={onChange}>
        {options.map((option) => (
          <Styled.DropDownOption key={option.label} value={option.value}>
            {option.label}
          </Styled.DropDownOption>
        ))}
      </Styled.DropDownSelect>
    </Styled.DropDownWrapper>
  );
}
