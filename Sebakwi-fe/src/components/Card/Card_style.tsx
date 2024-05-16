import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

interface CardContentProps {
  width: string;
  height: string;
  $padding?: string;
  $gridArea?: string;
}

export const CardContainer = styled.div`
  /* margin-right: 20px; */
  margin-right: 1.5rem;
`;

export const CardTitle = styled.div`
  color: ${PALETTE.MAIN_BLUE};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 1rem;
  margin-bottom: 15px;
`;

export const CardContent = styled.div<CardContentProps>`
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  grid-area: ${(props) => props.$gridArea};
  background-color: white;
  border-radius: 5px;
  box-shadow: 2px 2px 2px #e9ecf0;
  padding: ${(props) => props.$padding || '20px'};
`;
