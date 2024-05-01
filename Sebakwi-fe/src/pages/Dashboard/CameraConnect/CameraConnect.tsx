import Camera from 'components/Camera/Camera';
import * as Styled from './CameraConnect_style';

export default function CameraConnect() {
  return (
    <Styled.Container>
      <Styled.FoupStocker>
        <Camera title="FoupStocker"></Camera>
      </Styled.FoupStocker>
      <Styled.Cleaning>
        <Camera title="Cleaning"></Camera>
      </Styled.Cleaning>
      <Styled.Etching>
        <Camera title="Ethcing"></Camera>
      </Styled.Etching>
      <Styled.Photo>
        <Camera title="Photo"></Camera>
      </Styled.Photo>
      <Styled.ConnectLine>
        <img src="images/cameraConnection.png" alt="cameraline" width={330} height={230} />
      </Styled.ConnectLine>
      <Styled.ColorInfo>dd</Styled.ColorInfo>
    </Styled.Container>
  );
}
