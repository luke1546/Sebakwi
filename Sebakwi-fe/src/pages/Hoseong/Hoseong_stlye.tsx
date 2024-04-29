import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

export const Title = styled.p`
  color: ${PALETTE.LIGHT_BLACK};
  font-size: 2rem;
  font-family: ${Noto_Sans_KR.regular.variable};
  font-weight: ${Noto_Sans_KR.regular.weight};
`;

export const Button = styled.button`
display: inline-flex;
align-items: center;
outline: none;
border: none;
border-radius: 4px;
color: white;
font-weight: bold;
cursor: pointer;
padding-left: 1rem;
padding-right: 1rem;


/*크기*/
height: 2.25rem;
font-size: 1rem;

/*색상 */
background: #228be6;
&:hover{
    background: #339af0;
}
&:active{
    background: #1c7ed6;
}

/*기타 */
& + & {
    margin-left: 1rem;
}
`;
