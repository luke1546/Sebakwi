export interface PaginationProps {
  totalPages: number;
  currentPageprop: number;
  onPageChange: (page: number) => void;
}
