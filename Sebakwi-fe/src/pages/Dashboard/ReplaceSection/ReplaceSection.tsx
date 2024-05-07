import React from 'react';
import * as Styled from './ReplaceSection_style';

interface Replace {
  wheelNumber: string;
  date: string;
}

function ReplaceComponent({ wheelNumber, date }: Replace) {
  return (
    <Styled.ReplaceWrapper>
      <Styled.WheelNumber>{wheelNumber}</Styled.WheelNumber>
      <Styled.Date>{date}</Styled.Date>
    </Styled.ReplaceWrapper>
  );
}

export default function ReplaceSection() {
  return (
    <div>
      <Styled.ReplaceHeader>
        <span>휠 번호</span>
        <span>교체 일자</span>
      </Styled.ReplaceHeader>
      <Styled.ReplaceContainer>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
        <ReplaceComponent wheelNumber="WH0001" date="24.02.22"></ReplaceComponent>
      </Styled.ReplaceContainer>
    </div>
  );
}
