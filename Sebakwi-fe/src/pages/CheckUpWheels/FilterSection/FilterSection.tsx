import { CheckBox } from 'components';
import React, { useEffect, useState } from 'react';
import * as Styled from './FilterSection_style';

export default function FilterSection() {
  const [selectedBoxId, setSelectedBoxId] = useState<number | null>(null);

  const handleCheckboxChange = (id: number) => {
    setSelectedBoxId(id === selectedBoxId ? null : id);
  };

  useEffect(() => {
    console.log(selectedBoxId);
  });

  return (
    <Styled.Wrapper>
      <Styled.CheckBoxWrapper>
        {CheckOrChange.map((name, index) => (
          <CheckBox
            name={name}
            id={index}
            key={name}
            checked={selectedBoxId === index}
            onChange={(index) => handleCheckboxChange(index)}
          />
        ))}
      </Styled.CheckBoxWrapper>
    </Styled.Wrapper>
  );
}

const CheckOrChange = ['검진 일시', '교체 일자'];
