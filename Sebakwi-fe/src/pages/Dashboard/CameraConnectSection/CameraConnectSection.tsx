import Camera from 'components/Camera/Camera';
import * as Styled from './CameraConnectSection_style';
import { PALETTE } from 'styles';
// import { useState } from 'react';
// import axios from 'axios';

// 카메라 연결현황 api 받고 props로 전달해주기(styled도)

export default function CameraConnectSection() {
  // const [cameras, setCameras] = useState([]);

  // isActive 상태가 변경될 때마다 실행
  // useEffect(() => {
  //   axios
  //     .get('')
  //     .then((response) => {
  //       setCameras(response.data); // 불러온 cameras 상태를 저장
  //     })
  //     .catch((err) => console.error('Error: ', err));
  // }, []);

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
      {/* api 연결 후 */}
      {/* {cameras.map((camera) => {
        <Styled.CameraWrapper id={camera.id}>
          <Camera title={camera.id.toUpperCase()} isActive={camera.status === 1} />
        </Styled.CameraWrapper>
      })} */}
      <Styled.ConnectLine>
        <img src="images/cameraConnection.png" alt="cameraline" width={330} height={230} />
      </Styled.ConnectLine>
      <Styled.Info>
        <Styled.ColorInfo>
          <Styled.InfoCircle color={PALETTE.MAIN_BLUE}></Styled.InfoCircle>
          <Styled.InfoText>ON</Styled.InfoText>
        </Styled.ColorInfo>
        <Styled.ColorInfo>
          <Styled.InfoCircle color={PALETTE.LIGHT_BLACK}></Styled.InfoCircle>
          <Styled.InfoText>OFF</Styled.InfoText>
        </Styled.ColorInfo>
      </Styled.Info>
    </Styled.Container>
  );
}
