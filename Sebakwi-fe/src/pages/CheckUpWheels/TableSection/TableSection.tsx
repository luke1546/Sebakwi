import { useState } from 'react';
import * as Styled from './TableSection_style';
import * as Comp from 'components';
import Modal from '../../Hoseong/Modal';

interface TableSectionProps {
  filter: {
    isCheckedDate: boolean;
    startDateTime: string;
    endDateTime: string;
    onlyAbnormal: boolean;
    position: number;
    ohtSerialNumber: string;
    desc: boolean;
  };
}

export default function TableSection(props : TableSectionProps) {
  const { filter } = props;

  const positionLabels: { [key: number]: string } = {
    1: 'LF', // Left Front
    2: 'RF', // Right Front
    3: 'LR', // Left Rear
    4: 'RR', // Right Rear
  };

  const posts = generateData();
  const [currentPage, setCurrentPage] = useState(1); // -1 페이지를 가져오게 해야함
  const pageSize = 20;
  const totalPages = Math.ceil(posts.length / pageSize);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };

  // 현재 페이지 데이터 가져오기
  const currentData = () => {
    const begin = (currentPage - 1) * pageSize;
    const end = begin + pageSize;
    return posts.slice(begin, end);
  };

  return (
    <Styled.Wrapper>
      <Styled.Table>
        <thead>
          <Styled.AttributesRow>
            <Styled.AttributesTitle width={35}>No.</Styled.AttributesTitle>
            <Styled.AttributesTitle width={70}>검진 ID</Styled.AttributesTitle>
            <Styled.AttributesTitle width={35}>휠 위치</Styled.AttributesTitle>
            <Styled.AttributesTitle width={90}>OHT 호기</Styled.AttributesTitle>
            <Styled.AttributesTitle width={90}>대시보드</Styled.AttributesTitle>
            <Styled.AttributesTitle width={50}>검사 결과</Styled.AttributesTitle>
            <Styled.AttributesTitle width={80}>교체일자</Styled.AttributesTitle>
          </Styled.AttributesRow>
        </thead>

        <tbody>
          {currentData().map((item, index) => (
            <Styled.TableTuple key={index} onClick={openModal}>
              <Styled.AttributesValue>
                {(currentPage - 1) * pageSize + index + 1}
              </Styled.AttributesValue>
              <Styled.AttributesValue>{item.wheelNumber}</Styled.AttributesValue>
              <Styled.AttributesValue>{positionLabels[item.position]}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.ohtNumber}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.checkedDate}</Styled.AttributesValue>
              <Styled.AttributesValue>
                {item.status === 'NORMAL' ? '정상' : '비정상'}
              </Styled.AttributesValue>
              <Styled.AttributesValue>{item.createdDate}</Styled.AttributesValue>
            </Styled.TableTuple>
          ))}
        </tbody>
      </Styled.Table>
      <Comp.Pagination totalPages={totalPages} onPageChange={setCurrentPage} />
      {isModalOpen && <Modal onClose={closeModal} />}
    </Styled.Wrapper>
  );
}

const generateData = () => {
  return Array.from({ length: 140 }, (_, index) => ({
    checkupListId: index + 1,
    wheelNumber: `SM00${232 + index}`,
    position: (index % 4) + 1, // 예를 들어 1부터 5까지 반복
    ohtNumber: `VM00${4 + index}`,
    checkedDate: `2024-05-${Math.floor(index / 2) + 1} 10:53:38`, // 날짜도 약간 변동을 주어서 생성
    status: index % 2 === 0 ? 'NORMAL' : 'ABNORMAL',
    createdDate: `2024-05-${Math.floor(index / 2) + 1}`,
  }));
};
