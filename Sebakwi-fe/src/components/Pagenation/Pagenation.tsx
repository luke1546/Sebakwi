import React, { useState } from 'react';
import * as Styled from './Pagenation_style';

interface PaginationProps {
  totalPages: number;
  onPageChange: (page: number) => void;
}

export default function Pagination(props: PaginationProps) {
  const { totalPages, onPageChange } = props;
  const pagesPerGroup = 5; // 페이지 그룹당 페이지 수를 5로 설정
  const [currentPage, setCurrentPage] = useState(1);
  const [currentGroup, setCurrentGroup] = useState(0);
  const totalGroups = Math.ceil(totalPages / pagesPerGroup);

  const goToPage = (page: number) => {
    setCurrentPage(page);
    onPageChange(page);
  };

  const goToFirstPage = () => {
    setCurrentPage(1);
    setCurrentGroup(0); 
  };

  const goToLastPage = () => {
    setCurrentPage(totalPages);
    setCurrentGroup(totalGroups - 1); 
  };

  const nextGroup = () => {
    if (currentGroup < totalGroups - 1) { 
      const nextGroupFirstPage = (currentGroup + 1) * pagesPerGroup + 1;
      setCurrentGroup(currentGroup + 1);
      setCurrentPage(nextGroupFirstPage);
    }
  };

  const previousGroup = () => {
    if (currentGroup > 0) {
      const previousGroupFirstPage = (currentGroup - 1) * pagesPerGroup + 1;
      setCurrentGroup(currentGroup - 1);
      setCurrentPage(previousGroupFirstPage);
    }
  };

  const startPage = currentGroup * pagesPerGroup + 1;
  const endPage = Math.min(startPage + pagesPerGroup - 1, totalPages);
  return (
    <Styled.PagenationWrapper>
      <Styled.Button onClick={goToFirstPage}>{'<<'}</Styled.Button>
      <Styled.Button onClick={previousGroup}>{'<'}</Styled.Button>
      {Array.from({ length: endPage - startPage + 1 }, (_, i) => (
        <Styled.Button
          key={startPage + i}
          onClick={() => goToPage(startPage + i)}
          className={currentPage === startPage + i ? 'selected' : ''}
        >
          {startPage + i}
        </Styled.Button>
      ))}
      <Styled.Button onClick={nextGroup}>{'>'}</Styled.Button>
      <Styled.Button onClick={goToLastPage}>{'>>'}</Styled.Button>
    </Styled.PagenationWrapper>
  );
}
