import styled from 'styled-components';
import { PALETTE } from 'styles';

export const Wrapper = styled.div`
  height: 100%;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: start;
  padding: 40px 60px 40px 50px;
  background-color: ${PALETTE.LIGHT_BLUE};
`;

export const FilterSectionWrapper = styled.div`
  margin-bottom: 35px;
`;

