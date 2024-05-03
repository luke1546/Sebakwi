import React from 'react';
import * as Comp from 'components';
import * as Styled from './Jihun_style';

export default function JihunPage() {
  const dateTypes = [
    { label: '검진 날짜', value: 'creation' },
    { label: '교체 날짜', value: 'modification' },
  ];  
  return (
    <>
      <Comp.DropDownFilter title="날짜 유형" options={dateTypes} />
    </>
  );
}
