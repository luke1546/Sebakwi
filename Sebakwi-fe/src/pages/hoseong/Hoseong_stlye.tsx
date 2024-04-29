import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Title = styled.p`
  color: ${PALETTE.LIGHT_BLACK};
  font-size: 2rem;
  font-family: ${Noto_Sans_KR.regular.variable};
  font-weight: ${Noto_Sans_KR.regular.weight};
`;
