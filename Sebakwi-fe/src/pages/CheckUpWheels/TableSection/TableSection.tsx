import { useState } from 'react';
import * as Styled from './TableSection_style';
import Modal from '../../Hoseong/Modal';

export default function TableSection() {
  const positionLabels: { [key: number]: string } = {
    0: 'LF', // Left Front
    1: 'RF', // Right Front
    2: 'LR', // Left Rear
    3: 'RR', // Right Rear
  };

  const posts = generateData();
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 13;
  const totalPages = Math.ceil(posts.length / pageSize);
  const [pageGroup, setPageGroup] = useState(0);
  const groupSize = 5; // 한 번에 보여줄 페이지 수

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

  const goToPage = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  const nextPageGroup = () => {
    setPageGroup((prev) => prev + 1);
  };

  const prevPageGroup = () => {
    setPageGroup((prev) => prev - 1);
  };

  return (
    <>
      <table>
        <Styled.AttributesRow>
          <Styled.AttributesTitle>No.1</Styled.AttributesTitle>
          <Styled.AttributesTitle>검진 ID</Styled.AttributesTitle>
          <Styled.AttributesTitle>위치</Styled.AttributesTitle>
          <Styled.AttributesTitle>OHT 호기</Styled.AttributesTitle>
          <Styled.AttributesTitle>검진 일시</Styled.AttributesTitle>
          <Styled.AttributesTitle>검사 결과</Styled.AttributesTitle>
          <Styled.AttributesTitle>교체일자</Styled.AttributesTitle>
          <Styled.AttributesTitle>상세</Styled.AttributesTitle>
        </Styled.AttributesRow>

        <tbody>
          {currentData().map((item, index) => (
            <tr key={index}>
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
              <Styled.AttributesValue>
                <button onClick={openModal}>상세</button>
              </Styled.AttributesValue>
            </tr>
          ))}
        </tbody>
      </table>
      <div style={{ marginTop: '20px' }}>
        {pageGroup > 0 && <button onClick={prevPageGroup}>&laquo;</button>}
        {Array.from({ length: Math.min(groupSize, totalPages - pageGroup * groupSize) }, (_, i) => (
          <button key={i} onClick={() => goToPage(pageGroup * groupSize + i + 1)}>
            {pageGroup * groupSize + i + 1}
          </button>
        ))}
        {(pageGroup + 1) * groupSize < totalPages && (
          <button onClick={nextPageGroup}>&raquo;</button>
        )}
      </div>
      {isModalOpen && <Modal onClose={closeModal} />}
    </>
  );
}

// const data = [
//   {
//     checkupListId: 8,
//     wheelNumber: 'SM0013',
//     position: 3,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:35:23',
//     status: 'ABNORMAL',
//     createdDate: '2023-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'ABNORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'NORMAL',
//     createdDate: '2024-03-28',
//   },
//   {
//     checkupListId: 7,
//     wheelNumber: 'SM0016',
//     position: 2,
//     ohtNumber: 'VM0005',
//     checkedDate: '2024-04-17 11:36:09',
//     status: 'ABNORMAL',
//     createdDate: '2024-03-28',
//   },
// ];

const generateData = () => {
  return Array.from({ length: 140 }, (_, index) => ({
    checkupListId: index + 1,
    wheelNumber: `SM00${232 + index}`,
    position: index % 4, // 예를 들어 1부터 5까지 반복
    ohtNumber: `VM00${4 + index}`,
    checkedDate: `2024-05-${Math.floor(index / 2) + 1} 10:53:38`, // 날짜도 약간 변동을 주어서 생성
    status: index % 2 === 0 ? 'NORMAL' : 'ABNORMAL',
    createdDate: `2024-05-${Math.floor(index / 2) + 1}`,
  }));
};
