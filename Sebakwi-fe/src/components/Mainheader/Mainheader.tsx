import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import * as Styled from './Mainheader_style';

export default function Mainheader() {
  const location = useLocation();

  return (
    <Styled.MainheaderContainer>
      <Styled.LogoWrapper>
        <Link to="/">
          <img src="/images/semesLogo.png" alt="Logo" width={120} height={28} />
        </Link>
      </Styled.LogoWrapper>
      <Styled.StyledLink to="/" isActive={location.pathname === '/'}>
        대시보드
      </Styled.StyledLink>
      <Styled.StyledLink
        to="/checkupwheels"
        isActive={location.pathname === '/checkupwheels'}
      >
        검진 목록
      </Styled.StyledLink>
      <Styled.StyledLink
        to="/statistics"
        isActive={location.pathname === '/statistics'}
      >
        통계
      </Styled.StyledLink>
      <Styled.StyledLink to="/jihun" isActive={location.pathname === '/jihun'}>
        jihun
      </Styled.StyledLink>
      <Styled.StyledLink to="/jiwon" isActive={location.pathname === '/jiwon'}>
        jiwon
      </Styled.StyledLink>
      <Styled.StyledLink
        to="/hoseong"
        isActive={location.pathname === '/hoseong'}
      >
        hoseong
      </Styled.StyledLink>

      <Styled.StyledLink
        to="/example"
        isActive={location.pathname === '/example'}
      >
        Example
      </Styled.StyledLink>
    </Styled.MainheaderContainer>
  );
}
