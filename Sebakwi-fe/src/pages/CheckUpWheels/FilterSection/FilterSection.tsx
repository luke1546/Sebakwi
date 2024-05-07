import React, { useEffect, useState } from 'react';
import * as Styled from './FilterSection_style';
import * as Comp from 'components';
import { Filters } from '../CheckUpWheels';

interface FilterSectionProps {
  filter: Filters;
  onSubmitFilters: (filters: Filters) => void; // 데이터 조회 함수 추가
}

export default function FilterSection(props: FilterSectionProps) {
  const { filter, onSubmitFilters } = props;

  const [localFilters, setLocalFilters] = useState<Filters>(filter);
  const handleUpdateLocalFilter = (filterName: keyof Filters, value: Filters[keyof Filters]) => {
    setLocalFilters((prev) => ({
      ...prev,
      [filterName]: value,
    }));
  };

  const handleSubmit = () => {
    onSubmitFilters(localFilters);
  };

  const handleReset = () => {
    setLocalFilters(filter);
  };

  return (
    <Styled.Wrapper>
      <Styled.FilterWrapper>
        <Comp.FilterInput title="날짜 유형">
          <Comp.DropDownFilter
            options={dateTypes}
            value={localFilters.selectedDateType}
            onChange={(e) => handleUpdateLocalFilter('selectedDateType', Number(e.target.value))}
          />
        </Comp.FilterInput>

        <Comp.FilterInput title="날짜 기간">
          <Comp.CheckBox
            name="전체"
            checked={localFilters.selectedTimeCheck === 1 ? 1 : 0}
            onChange={(e) => handleUpdateLocalFilter('selectedTimeCheck', e.target.checked ? 1 : 0)}
          />
          <Styled.DatePickerContainer>
            <Styled.DatePickerInputWrapper>
              <p>{filter.startDateTime}</p>
            </Styled.DatePickerInputWrapper>
            <img src="/images/calender.png" alt="123" width={14} height={16} />
          </Styled.DatePickerContainer>
          <Styled.RangeWrapper>
            <p>~</p>
          </Styled.RangeWrapper>
          <Styled.DatePickerContainer>
            <Styled.DatePickerInputWrapper>
              <p>{filter.endDateTime}</p>
            </Styled.DatePickerInputWrapper>
            <img src="/images/calender.png" alt="123" width={14} height={16} />
          </Styled.DatePickerContainer>
        </Comp.FilterInput>
      </Styled.FilterWrapper>

      {/* -----------구분선 --------- */}

      <Styled.FilterSearchWrapper>
        <Styled.LeftContentWrapper>
          <Comp.FilterInput title="휠 위치">
            <Comp.DropDownFilter
              options={wheelPositions}
              value={localFilters.selectedWheelPosition}
              onChange={(e) =>
                handleUpdateLocalFilter('selectedWheelPosition', Number(e.target.value))
              }
            />
          </Comp.FilterInput>

          <Comp.FilterInput title="정렬 기준">
            <Comp.DropDownFilter
              options={sortTypes}
              value={localFilters.selectedSortType}
              onChange={(e) => handleUpdateLocalFilter('selectedSortType', Number(e.target.value))}
            />
          </Comp.FilterInput>
          <Comp.FilterInput title="OHT 호기">
            <Styled.DatePickerContainer>
              <Styled.DatePickerInputWrapper>
                <p>VM1233</p>
              </Styled.DatePickerInputWrapper>
            </Styled.DatePickerContainer>
          </Comp.FilterInput>

          <Comp.CheckBox
            name="비정상 결과만 보기"
            checked={localFilters.selectedAbnormal === 1 ? 1 : 0}
            onChange={(e) => handleUpdateLocalFilter('selectedAbnormal', e.target.checked ? 1 : 0)}
          />
        </Styled.LeftContentWrapper>

        <Styled.RightContentWrapper>
          <Styled.SearchButton type="button" onClick={handleSubmit}>
            <Styled.SearchButtonText>조회</Styled.SearchButtonText>
          </Styled.SearchButton>
          <Styled.ResetButton type="button" onClick={handleReset}>
            <Styled.ResetButtonText>초기화</Styled.ResetButtonText>
          </Styled.ResetButton>
        </Styled.RightContentWrapper>
      </Styled.FilterSearchWrapper>
    </Styled.Wrapper>
  );
}

const dateTypes = [
  { label: '검진 날짜', value: 0 },
  { label: '교체 날짜', value: 1 },
];

const wheelPositions = [
  { label: '전체', value: 0 },
  { label: 'LF', value: 1 },
  { label: 'RF', value: 2 },
  { label: 'LR', value: 3 },
  { label: 'RR', value: 4 },
];

const sortTypes = [
  { label: '최신 순', value: 0 },
  { label: '오래된 순', value: 1 },
];
