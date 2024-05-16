import React from 'react';
import { FilterInputProps } from 'types';
import * as Styled from './FilterInput_style';


export default function FilterInput(props: FilterInputProps) {
  const { title, children } = props;
  return (
    <Styled.FilterInputWrapper>
      <Styled.FilterInputTitle>{title}</Styled.FilterInputTitle>
      {children}
    </Styled.FilterInputWrapper>
  );
}
