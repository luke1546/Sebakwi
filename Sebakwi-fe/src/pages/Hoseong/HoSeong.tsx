import { useState } from 'react';
import * as Styled from './Hoseong_stlye';
import Modal from './Modal';

export default function HoseongPage() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  }
  const closeModal = () => {
    setIsModalOpen(false);
  }
  return (
    <>
      <Styled.Title>호성이 페이지</Styled.Title>

      <Styled.Button onClick={openModal}>BUTTON</Styled.Button>
      {isModalOpen && <Modal onClose={closeModal} id={1} />}
    </>
  );
}
