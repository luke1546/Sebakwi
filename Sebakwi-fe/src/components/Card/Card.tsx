import { CardProps } from 'types';
import * as Styled from './Card_style';

// title, width, height, padding(기본값 20) 적고 안에 children 작성해서 사용
export default function Card(props: CardProps) {
  const { title, children, width, height, padding } = props;
  return (
    <Styled.CardContainer>
      {title && <Styled.CardTitle>{title}</Styled.CardTitle>}
      <Styled.CardContent width={width} height={height} $padding={padding}>
        {children}
      </Styled.CardContent>
    </Styled.CardContainer>
  );
}
