import React from 'react';
import * as Styled from './Card_style';

// card 컴포넌트의 props의 타입을 정의
type CardProps = {
  title: string;
  children: React.ReactNode;
  width: string;
  height: string;
};

// title, width, height 적고 안에 children 작성해서 사용
export default function Card({ title, children, width, height }: CardProps) {
  return (
    <Styled.CardContainer>
      <Styled.CardTitle>{title}</Styled.CardTitle>
      <Styled.CardContent width={width} height={height}>
        {children}
      </Styled.CardContent>
    </Styled.CardContainer>
  );
}
