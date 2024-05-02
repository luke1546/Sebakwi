import React, { useState } from 'react';
import * as Styled from './TableSection_style';
import Modal from '../../Hoseong/Modal';

export default function TableSection() {
  const positionLabels: { [key: number]: string } = {
    0: 'LF', // Left Front
    1: 'RF', // Right Front
    2: 'LR', // Left Rear
    3: 'RR', // Right Rear
  };

  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
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
          {data.map((item, index) => (
            <tr key={index}>
              <Styled.AttributesValue>{index + 1}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.wheelNumber}</Styled.AttributesValue>
              <Styled.AttributesValue>{positionLabels[item.position]}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.ohtNumber}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.checkedDate}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.status}</Styled.AttributesValue>
              <Styled.AttributesValue>{item.createdDate}</Styled.AttributesValue>
              <Styled.AttributesValue>
                <button onClick={openModal}>상세</button>
              </Styled.AttributesValue>{' '}
              {/* 상세 정보를 나중에 추가할 수 있습니다 */}
            </tr>
          ))}
        </tbody>
      </table>
      <>{isModalOpen && <Modal onClose={closeModal} />}</>
    </>
  );
}

const data = [
  {
    checkupListId: 8,
    wheelNumber: 'SM0013',
    position: 3,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:35:23',
    status: 'ABNORMAL',
    createdDate: '2023-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'ABNORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'NORMAL',
    createdDate: '2024-03-28',
  },
  {
    checkupListId: 7,
    wheelNumber: 'SM0016',
    position: 2,
    ohtNumber: 'VM0005',
    checkedDate: '2024-04-17 11:36:09',
    status: 'ABNORMAL',
    createdDate: '2024-03-28',
  },
];
