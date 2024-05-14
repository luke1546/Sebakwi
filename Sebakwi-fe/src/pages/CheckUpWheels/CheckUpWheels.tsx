import { useEffect, useState } from 'react';
import TableSection from './TableSection/TableSection';
import FilterSection from './FilterSection/FilterSection';
import { GoAlert } from 'react-icons/go';
import { Filters, WheelData } from 'types';
import { Slide, toast } from 'react-toastify';
import * as Comp from 'components';
import * as Styled from './CheckUpWheels_style';

let shownAlerts = new Set();

export default function CheckUpWheelsPage() {
  const [filters, setFilters] = useState<Filters>({
    selectedDateType: 0, // 0: 검진 날짜, 1: 교체 날짜
    selectedTimeCheck: 1, // 1: 날짜 기간 전체 체크, 0: 날짜 기간 직접 입력
    startDateTime: new Date().toISOString().slice(0, 10), // 시작 날짜
    endDateTime: new Date().toISOString().slice(0, 10), // 끝 날짜
    selectedWheelPosition: 0, // 0: 전체, 1: LF, 2: RF, 3: LR, 4: RR
    selectedSortType: 0, // 0: 최신 순, 1: 오래된 순
    selectedAbnormal: 0, // 비정상 결과만 보기 체크
    OHTSerialNumber: '', // OHT 호기
    WheelSerialNumber: '', // Wheel 호기
  });

  const Resetfilters = {
    selectedDateType: 0, // 0: 검진 날짜, 1: 교체 날짜
    selectedTimeCheck: 1, // 1: 날짜 기간 전체 체크, 0: 날짜 기간 직접 입력
    startDateTime: new Date().toISOString().slice(0, 10), // 시작 날짜
    endDateTime: new Date().toISOString().slice(0, 10), // 끝 날짜
    selectedWheelPosition: 0, // 0: 전체, 1: LF, 2: RF, 3: LR, 4: RR
    selectedSortType: 0, // 0: 최신 순, 1: 오래된 순
    selectedAbnormal: 0, // 비정상 결과만 보기 체크
    OHTSerialNumber: '', // OHT 호기
    WheelSerialNumber: '', // Wheel 일련번호
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
    ohtSerialNumber: filters.OHTSerialNumber,
    WheelSerialNumber: filters.WheelSerialNumber,
    desc: filters.selectedSortType === 0,
  };

  useEffect(() => {
    //SSE 연결
    const baseUrl = process.env.REACT_APP_BASE_URL;
    const eventSource = new EventSource(`${baseUrl}/wheels/monthly/sse`);

    eventSource.addEventListener('sse', (event) => {
      const newMessage: WheelData = JSON.parse(event.data);
      if (typeof newMessage == 'string') console.log(newMessage);
      else {
        console.log('데이터 : ' + event.data);

        // 토스트 알림
        if (newMessage.wheelList.length > 0) {
          const newWheelData = newMessage.wheelList[newMessage.wheelList.length - 1];
          const alertKey = `${newWheelData.wheelNumber} - ${newWheelData.crack ? 'crack' : ''}${
            newWheelData.stamp ? 'stamp' : ''
          }${newWheelData.peeling ? 'peeling' : ''}`;

          if (!shownAlerts.has(alertKey)) {
            shownAlerts.add(alertKey);
            if (newWheelData.crack) {
              toast.error(`${newWheelData.wheelNumber} 휠 크랙 발생`, { icon: <GoAlert /> });
            }
            if (newWheelData.stamp) {
              toast.error(`${newWheelData.wheelNumber} 휠 찍힘 발생`, { icon: <GoAlert /> });
            }
            if (newWheelData.peeling) {
              toast.error(`${newWheelData.wheelNumber} 휠 박리 발생`, { icon: <GoAlert /> });
            }
          }
        }
      }
    });

    return () => {
      eventSource.close();
    };
  }, []);

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
        <TableSection Filter={transformedFilters} />
      </Comp.Card>
      <Styled.AlarmContainer
        position="bottom-right"
        autoClose={4000}
        hideProgressBar
        newestOnTop={false}
        closeOnClick={false}
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={false}
        pauseOnHover
        theme="colored"
        transition={Slide}
      />
    </Styled.Wrapper>
  );
}
