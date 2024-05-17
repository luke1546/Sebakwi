import { useEffect, useState } from 'react';
import axios from 'axios';
import { TableSectionProps, CheckupListItem } from 'types';
import { positionLabels } from 'constant';
import * as Comp from 'components';
import * as Styled from './TableSection_style';

export default function TableSection(props: TableSectionProps) {
  const { Filter } = props;

  const [data, setData] = useState<CheckupListItem[]>([]);

  const [currentPage, setCurrentPage] = useState(1);
  const [totalData, setTotalData] = useState<number>(0);
  const [totalPages, setTotalPages] = useState(0);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedItemId, setSelectedItemId] = useState<number | null>(null);

  const openModal = (Id: number) => {
    setSelectedItemId(Id);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  
  useEffect(() => {
    async function fetchData() {
      try {
        const baseUrl = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${baseUrl}/checkup_list`, {
          params: {
            isCheckedDate: Filter.isCheckedDate,
            startDateTime: Filter.startDateTime,
            endDateTime: Filter.endDateTime,
            onlyAbnormal: Filter.onlyAbnormal,
            position: Filter.position,
            page: currentPage - 1,
            sortByCheck: true,
            ohtSerialNumber: Filter.ohtSerialNumber,
            wheelSerialNumber: Filter.WheelSerialNumber,
            desc: Filter.desc,
          },
        });
        setData(response.data.checkupListArray);
        setTotalPages(response.data.totalPages);
        setTotalData(response.data.totalCount);
      } catch (error) {
        setData([]);
      }
    }

    fetchData();
  }, [Filter, currentPage]);

  return (
    <Styled.Wrapper>
      {totalData > 0 && <Styled.TotalcountWrapper>총 {totalData} 건</Styled.TotalcountWrapper>}

      <Styled.Table>
        <thead>
          <Styled.AttributesRow>
            <Styled.AttributesTitle width={35}>검진 ID</Styled.AttributesTitle>
            <Styled.AttributesTitle width={70}>휠 일련번호</Styled.AttributesTitle>
            <Styled.AttributesTitle width={35}>휠 위치</Styled.AttributesTitle>
            <Styled.AttributesTitle width={70}>OHT 호기</Styled.AttributesTitle>
            <Styled.AttributesTitle width={90}>검진 일자</Styled.AttributesTitle>
            <Styled.AttributesTitle width={50}>검사 결과</Styled.AttributesTitle>
            <Styled.AttributesTitle width={80}>교체 일자</Styled.AttributesTitle>
          </Styled.AttributesRow>
        </thead>

        <tbody>
          {data.length > 0 ? (
            data.map((item, index) => (
              <Styled.TableTuple
                key={index}
                onClick={() => openModal(item.checkupListId)}
                $status={item.status}
              >
                <Styled.AttributesValue>{item.checkupListId}</Styled.AttributesValue>
                <Styled.AttributesValue>{item.wheelNumber}</Styled.AttributesValue>
                <Styled.AttributesValue>{positionLabels[item.position]}</Styled.AttributesValue>
                <Styled.AttributesValue>{item.ohtNumber}</Styled.AttributesValue>
                <Styled.AttributesValue>{item.checkedDate}</Styled.AttributesValue>
                <Styled.AttributesValue>
                  {item.status === 'NORMAL' ? '정상' : '비정상'}
                </Styled.AttributesValue>
                <Styled.AttributesValue>{item.createdDate}</Styled.AttributesValue>
              </Styled.TableTuple>
            ))
          ) : (
            <tr>
              <Styled.NoDataTd colSpan={7}>데이터가 없습니다.</Styled.NoDataTd>
            </tr>
          )}
        </tbody>
      </Styled.Table>
      <Comp.Pagination totalPages={totalPages} onPageChange={setCurrentPage} />
      {isModalOpen && <Comp.Modal onClose={closeModal} id={selectedItemId} />}
    </Styled.Wrapper>
  );
}
