import React from 'react';
import * as Comp from 'components'
import * as Styled from './Jihun_style';

export default function JihunPage() {
  return (
    <>
      <Styled.Title>지훈이 페이지</Styled.Title>
      <Styled.CustomButton
        type="button"
        onClick={() => {
          console.log('바보');
        }}
      >
        오지훈
      </Styled.CustomButton>
    </>
  );
}
