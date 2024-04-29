import * as Styled from './CheckUpWheels_style';
import FilterSection from './FilterSection/FilterSection';
import TableSection from './TableSection/TableSection';

export default function CheckUpWheelsPage() {
  return (
    <Styled.Wrapper>
      <FilterSection />
      <TableSection />
    </Styled.Wrapper>
  );
}
