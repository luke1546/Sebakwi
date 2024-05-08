import styled from 'styled-components';
import { PALETTE } from 'styles';

export const Wrapper = styled.div`
  height: calc(100% - 72px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 40px 50px 40px 50px;
  background-color: ${PALETTE.LIGHT_BLUE};
`;

export const FilterSectionWrapper = styled.div`
  margin-bottom: 35px;
`;

