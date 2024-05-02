import React, { useState } from 'react';
import * as Styled from './Camera_style';
// import axios from 'axios';

// api 연결 고려해서 다시 짜기
// api 받아오기(props로 전달됨)
// 연결되면 파랑색, 아니면 회색
// api 보내기

type CameraTitle = {
  title: string;
};

export default function Camera({ title }: CameraTitle) {
  const [isActive, setIsActive] = useState(true);

  // function toggleIsActive() {
  //   axios
  //     .post('', { id: title.toLowerCase(), status: isActive ? 0 : 1 })
  //     .then((res) => {
  //       setIsActive(!isActive);
  //     })
  //     .catch((err) => console.error('Error : ', err));
  // }

  return (
    <Styled.CameraContainer>
      <Styled.CameraTitle>{title}</Styled.CameraTitle>
      <Styled.CameraImage>
        {isActive ? (
          <img src="/images/cameraBlue.png" alt="cameraBlue" width={60} height={45} />
        ) : (
          <img src="/images/cameraGrey.png" alt="cameraGrey" width={60} height={45} />
        )}
      </Styled.CameraImage>
      <Styled.ToggleSwitch>
        <Styled.CheckBox
          type="checkbox"
          checked={isActive}
          onChange={() => setIsActive(!isActive)}
        />
        <Styled.ToggleSlider />
      </Styled.ToggleSwitch>
    </Styled.CameraContainer>
  );
}
