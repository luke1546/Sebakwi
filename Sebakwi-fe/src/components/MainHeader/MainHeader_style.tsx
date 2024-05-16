import styled from 'styled-components';
import { Link, LinkProps } from 'react-router-dom';
import { Noto_Sans_KR, PALETTE } from 'styles';

interface StyledLinkProps extends LinkProps {
  isActive: boolean;
}

export const MainheaderContainer = styled.header`
  height: 72px;
  padding: 0px 35px;
  display: flex;
  align-items: center;
  justify-content: start;
  border-bottom: 2px solid ${PALETTE.MAIN_BLUE};
  position: relative;
`;

export const LogoWrapper = styled.div`
  padding-top: 5px;
  margin-right: 40px;
`;

export const StyledLink = styled(({ isActive, ...rest }: StyledLinkProps) => <Link {...rest} />)`
  color: ${({ isActive }) => (isActive ? PALETTE.MAIN_BLUE : PALETTE.MAIN_BLACK)};
  margin-right: 20px;
  text-decoration: none;
  font-size: 1.25rem;
  font-weight: ${Noto_Sans_KR.bold.weight};

  &:hover {
    color: ${PALETTE.MAIN_BLUE};
  }

  &:active {
    color: ${PALETTE.MAIN_BLACK};
  }
`;
