import React from 'react';
import * as Styled from './FilterInput_style';

interface FilterInputProps {
  title: string;
  children: React.ReactNode;
}

export default function FilterInput(props: FilterInputProps) {
  const { title, children } = props;
  return (
    <Styled.FilterInputWrapper>
      <Styled.FilterInputTitle>{title}</Styled.FilterInputTitle>
      {children}
    </Styled.FilterInputWrapper>
  );
}
