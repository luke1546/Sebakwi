import styled, { css } from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

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

// api 연결 후 styled-component 조건부 스타일링
// export const CameraWraaper = styled(CameraComponent)`
//   ${(props) =>
//     props.id === 'FoupStocker' &&
//     css`
//       grid-column: 1 / 2;
//       grid-row: 1 / 2;
//       margin-bottom: 10px;
//     `}
//   ${(props) =>
//     props.id === 'Etching' &&
//     css`
//       grid-column: 7 / 8;
//       grid-row: 1 / 2;
//       margin-bottom: 10px;
//     `}
//     ${(props) =>
//     props.id === 'Cleaning' &&
//     css`
//       grid-column: 1 / 2;
//       grid-row: 2 / 3;
//       margin-top: 100px;
//     `}
//     ${(props) =>
//     props.id === 'Photo' &&
//     css`
//       grid-column: 7 / 8;
//       grid-row: 2 / 3;
//       margin-top: 100px;
//     `}
// `;

export const ConnectLine = styled.div`
  grid-column: 3 / 7;
  grid-row: 1 / 4;
  margin-top: 10px;
  margin-right: 10px;
`;

// OFF, ON INFO
export const Info = styled.div`
  grid-column: 8 / 9;
  grid-row: 3 / 4;
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-left: 50px;
`;

export const ColorInfo = styled.div`
  display: flex;
  align-items: center;
  gap: 8px;
`;

export const InfoCircle = styled.div<{ color: string }>`
  border-radius: 50px;
  background-color: ${(props) => props.color};
  width: 12px;
  height: 12px;
`;

export const InfoText = styled.span`
  color: ${PALETTE.MAIN_BLACK};
  font-weight: ${Noto_Sans_KR.medium.weight};
  font-size: 0.6rem;
`;
