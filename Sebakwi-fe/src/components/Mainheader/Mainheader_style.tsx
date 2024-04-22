import styled from 'styled-components';
import { PALETTE } from '../../styles/colors';

export const MainheaderContainer = styled.div`
  width: 100vw;
  height: 72px;
  padding: 0px 48px;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid ${PALETTE.LIGHT_BLACK};
  position: relative;
  z-index: 99;
`;
