import { Filters } from 'types/pages/CheckUpWheels';

export interface FilterSectionProps {
  filter: Filters;
  onSubmitFilters: (filters: Filters) => void;
  onResetFilters: () => void;
}
