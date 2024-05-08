import * as Styled from './CheckUpWheels_style';
import * as Comp from 'components';
import FilterSection from './FilterSection/FilterSection';
import TableSection from './TableSection/TableSection';
import { useState } from 'react';

export interface Filters {
  selectedDateType: number;
  selectedTimeCheck: number;
  startDateTime: string;
  endDateTime: string;
  selectedWheelPosition: number;
  selectedSortType: number;
  selectedAbnormal: number;
  OHTLabel: string;
}

export default function CheckUpWheelsPage() {
  const [filters, setFilters] = useState<Filters>({
    selectedDateType: 0, // 0: 검진 날짜, 1: 교체 날짜
    selectedTimeCheck: 1, // 1: 날짜 기간 전체 체크, 0: 날짜 기간 직접 입력
    startDateTime: new Date().toISOString().slice(0, 10), // 시작 날짜
    endDateTime: new Date().toISOString().slice(0, 10), // 끝 날짜
    selectedWheelPosition: 0, // 0: 전체, 1: LF, 2: RF, 3: LR, 4: RR
    selectedSortType: 0, // 0: 최신 순, 1: 오래된 순
    selectedAbnormal: 0, // 비정상 결과만 보기 체크
    OHTLabel: '', // OHT 호기
  });

  const Resetfilters = {
    selectedDateType: 0, // 0: 검진 날짜, 1: 교체 날짜
    selectedTimeCheck: 1, // 1: 날짜 기간 전체 체크, 0: 날짜 기간 직접 입력
    startDateTime: new Date().toISOString().slice(0, 10), // 시작 날짜
    endDateTime: new Date().toISOString().slice(0, 10), // 끝 날짜
    selectedWheelPosition: 0, // 0: 전체, 1: LF, 2: RF, 3: LR, 4: RR
    selectedSortType: 0, // 0: 최신 순, 1: 오래된 순
    selectedAbnormal: 0, // 비정상 결과만 보기 체크
    OHTLabel: '', // OHT 호기
  };

  const UpdateFilter = (newFilters: Filters) => {
    setFilters(newFilters);
  };

  const ResetFilter = () => {
    setFilters(Resetfilters);
  };

  const transformedFilters = {
    isCheckedDate: filters.selectedTimeCheck === 1,
    startDateTime: filters.selectedTimeCheck === 1 ? '' : filters.startDateTime,
    endDateTime: filters.selectedTimeCheck === 1 ? '' : filters.endDateTime,
    onlyAbnormal: filters.selectedAbnormal === 1,
    position: filters.selectedWheelPosition,
    ohtSerialNumber: filters.OHTLabel,
    desc: filters.selectedSortType === 0,
  };

  return (
    <Styled.Wrapper>
      <Styled.FilterSectionWrapper>
        <Comp.Card width="100%" height="100%" padding="30px">
          <FilterSection
            filter={filters}
            onSubmitFilters={UpdateFilter}
            onResetFilters={ResetFilter}
          />
        </Comp.Card>
      </Styled.FilterSectionWrapper>
      <Comp.Card width="100%" height="90%" padding="30px">
        <TableSection filter={transformedFilters} />
      </Comp.Card>
    </Styled.Wrapper>
  );
}
