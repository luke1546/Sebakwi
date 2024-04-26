import * as Styled from './example_style';

interface ExamplePageProps {
  name?: string; // ? 붙이면 넣어도 되고 안넣어도 되고 (선택적)
}

// export default function ExamplePage({ name }: ExamplePageProps) {
//   const props = { name: name };
//   return (
//     <Styled.MainWrapper>
//       <h1>{props.name}</h1>
//     </Styled.MainWrapper>
//   );
// }

export default function ExamplePage(props: ExamplePageProps) {
  const { name } = props;
  return (
    <Styled.MainWrapper>
      <h1>{name}</h1>
    </Styled.MainWrapper>
  );
}

