import { CheckBox } from 'components';
import React, { useEffect, useState } from 'react';
import * as Styled from './FilterSection_style';

export default function FilterSection() {
  const [selectedBoxId, setSelectedBoxId] = useState<number | null>(0);
  const [selectedAbnormal, setSelectedAbnormal] = useState<boolean>(false);

  const handleCheckboxChange = (id: number) => {
    setSelectedBoxId(id === selectedBoxId ? null : id);
  };

  const handleAbnormalCheck = () => {
    setSelectedAbnormal(!selectedAbnormal);
  };

  useEffect(() => {
    console.log(selectedBoxId);
    console.log(selectedAbnormal);
  });

  return (
    <Styled.Wrapper>
      <Styled.CheckBoxWrapper>
        {CheckOrChange.map((name, index) => (
          <CheckBox
            name={name}
            key={name}
            checked={selectedBoxId === index}
            onChange={() => handleCheckboxChange(index)}
          />
        ))}
      </Styled.CheckBoxWrapper>
      <Styled.SearchWrapper>
        <Styled.LeftContentWrapper>
          <Styled.DateTimePickerWrapper>
            <Styled.DatePickerContainer>
              <Styled.DatePickerInputWrapper>
                <p>2024-04-29</p>
              </Styled.DatePickerInputWrapper>
              <img src="/images/calender.png" alt="123" width={14} height={16} />
            </Styled.DatePickerContainer>

            <Styled.TimePickerContainer>
              <Styled.TimePickerInputWrapper>
                <p>18:15:20</p>
              </Styled.TimePickerInputWrapper>
              <img src="/images/clock.png" alt="123" width={14} height={14} />
            </Styled.TimePickerContainer>
            <Styled.RangeWrapper>
              <p>~</p>
            </Styled.RangeWrapper>
            <Styled.DatePickerContainer>
              <Styled.DatePickerInputWrapper>
                <p>2024-04-29</p>
              </Styled.DatePickerInputWrapper>
              <img src="/images/calender.png" alt="123" width={14} height={16} />
            </Styled.DatePickerContainer>

            <Styled.TimePickerContainer>
              <Styled.TimePickerInputWrapper>
                <p>18:15:20</p>
              </Styled.TimePickerInputWrapper>
              <img src="/images/clock.png" alt="123" width={14} height={14} />
            </Styled.TimePickerContainer>
          </Styled.DateTimePickerWrapper>
          <Styled.CheckBoxWrapper>
            <CheckBox
              name="비정상 결과만 보기"
              checked={selectedAbnormal === true}
              onChange={() => handleAbnormalCheck()}
            />
          </Styled.CheckBoxWrapper>
        </Styled.LeftContentWrapper>
        <Styled.RightContentWrapper>
          <Styled.ResetButton type="button">
            <Styled.ResetButtonText>초기화</Styled.ResetButtonText>
          </Styled.ResetButton>
          <Styled.SearchButton type="button">
            <Styled.SearchButtonText>검색</Styled.SearchButtonText>
          </Styled.SearchButton>
        </Styled.RightContentWrapper>
      </Styled.SearchWrapper>
    </Styled.Wrapper>
  );
}

const CheckOrChange = ['검진 일시', '교체 일자'];
