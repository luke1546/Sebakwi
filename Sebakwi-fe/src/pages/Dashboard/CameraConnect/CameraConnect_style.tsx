import styled from 'styled-components';
import { PALETTE } from 'styles';

export const Container = styled.div`
  display: grid;
  grid-template-columns: repeat(8, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 10px;
  height: 100%; // 부모요소에 맞게 조정!
  place-items: center;
`;

export const CameraComponent = styled.div`
  width: 50px; // 지름 50px
  height: 50px; // 지름 50px
  border-radius: 50%; // 원형
`;

export const FoupStocker = styled(CameraComponent)`
  grid-column: 1 / 2;
  grid-row: 1 / 2;
  margin-bottom: 10px;
`;

export const Etching = styled(CameraComponent)`
  grid-column: 7 / 8;
  grid-row: 1 / 2;
  margin-bottom: 10px;
`;

export const Cleaning = styled(CameraComponent)`
  grid-column: 1 / 2;
  grid-row: 2 / 3;
  margin-top: 100px;
`;

export const Photo = styled(CameraComponent)`
  grid-column: 7 / 8;
  grid-row: 2 / 3;
  margin-top: 100px;
`;

export const ConnectLine = styled.div`
  grid-column: 3 / 7;
  grid-row: 1 / 4;
  margin-top: 10px;
  margin-right: 10px;
`;

export const ColorInfo = styled.div`
  grid-column: 8 / 9;
  grid-row: 3 / 4;
  /* width: 30px;
  height: 50px;
  position: absolute; // 상대적 위치 조정
  right: 0;
  margin-right: 20px; // 오른쪽에서 20px 떨어진 곳 */
`;
