import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';
import { BaseButton } from 'components';

export const Title = styled.p`
  color: ${PALETTE.LIGHT_BLACK};
  font-size: 2rem;
  font-family: ${Noto_Sans_KR.regular.variable};
  font-weight: ${Noto_Sans_KR.regular.weight};
`;

export const CustomButton = styled(BaseButton)`
  width: 100px;
  height: 25px;
  background-color: ${PALETTE.LIGHT_RED};
`;
