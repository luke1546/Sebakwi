import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Modal = styled.div`
  background-color: white;
  border-radius: 5px;
  /* width: 75%;
  height: 60%; */
  width: 1200px;
  height : 450px;
`;

export const ModalWrapper = styled.div`
position: fixed;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: rgba(0, 0, 0, 0.5);
display: flex;
justify-content: center;
align-items: center;
`;

export const Title = styled.div`
display: grid;
grid-template-columns: 20fr 1fr;
background-color: ${PALETTE.MAIN_BLUE};
padding: 15px 15px;
color: white;
align-items: center;
`

export const TitleInfo = styled.div`
    display:grid;
    grid-template-columns: auto auto auto auto auto auto auto;
    max-width: 700px;
`

export const Result = styled.span`
color : red;
`

export const Container = styled.div`
display: grid;
grid-template-rows: 1fr 12fr;
`

export const Content = styled.div`
display: grid;
grid-template-columns: 2fr 3fr 3fr;
`
export const SubContent = styled.div`
display: grid;
grid-template-rows: 1fr 15fr;
padding : 30px;
`

export const SubTitle = styled.div`
background-color: ${PALETTE.LIGHT_BLUE};
text-align: center;
color: ${PALETTE.MAIN_BLUE};
font-family: ${Noto_Sans_KR.extraBold.variable};
padding: 8px;
`

export const Image = styled.img`
width: 100%;
height: 250px;
`

export const ResultTable = styled.div`
table {
  border-collapse: collapse;
}
td, th {
  border: none; /* 테두리 없음 */
  width: 200px;
  height: 60px;
  text-align: center;
  /* padding: 15px 50px; */
  
}
td {
  border-bottom: 1px solid lightgray; /* 하단 경계선 */
  border-right: 1px solid lightgray;
}
tr:last-child>td{
    border-bottom: none;
}
td:last-child{
    border-right: none;
}
`

export const CheckboxWrapper = styled.label`
  display: inline-block;
  position: relative;
  padding-left: 25px;
  margin-right: 15px;
  cursor: pointer;
  user-select: none;
`;

export const CheckboxInput = styled.input`
  position: absolute;
  opacity: 0;
  cursor: pointer;
`;

export const CheckboxCheckmark = styled.span`
  position: absolute;
  top: 0;
  left: 0;
  height: 15px;
  width: 15px;
  background-color: #fff;
  border: 1px solid #000;
`;

export const ESCButton = styled.button`
    border: none;
    background-color: ${PALETTE.MAIN_BLUE};
    color: white;
    &:hover{cursor: pointer;
    }
`

// export const Wheel = styled.div`
//  width: 100px; /* 바퀴의 직경 */
//   height: 100px; /* 바퀴의 직경 */
//   background-color: #ccc; /* 바퀴 림 색상 */
//   border-radius: 50%; /* 원형으로 만들기 */
//   border: 10px solid black; /* 타이어를 표현하기 위한 테두리 */

//   position: relative; /* 내부 요소를 위해 상대적인 위치로 설정 */

//   /* 가상 요소를 사용하여 바퀴 스포크를 표현 */
//   &::before, &::after {
//     content: '';
//     position: absolute;
//     top: 50%;
//     left: 50%;
//     width: 60px; /* 스포크 길이 */
//     height: 6px; /* 스포크 두께 */
//     background-color: gray; /* 스포크 색상 */
//     transform-origin: center center;
//   }

//   &::before {
//     transform: translate(-50%, -50%) rotate(0deg); /* 수평 스포크 */
//   }

//   &::after {
//     transform: translate(-50%, -50%) rotate(90deg); /* 수직 스포크 */
//   }

//   /* 스포크를 추가하여 바퀴에 더 많은 디테일을 부여 */
//   &::nth-child(1) {
//     transform: translate(-50%, -50%) rotate(45deg);
//   }

//   &::nth-child(2) {
//     transform: translate(-50%, -50%) rotate(135deg);
//   }
// `;