import React, { useState } from 'react';
import * as Styled from './Camera_style';

// api 연결 고려해서 다시 짜기

type Camera = {
  title: string;
};

export default function Camera({ title }: Camera) {
  const [isActive, setIsActive] = useState(true);

  return (
    <Styled.CameraContainer>
      <Styled.CameraTitle>{title}</Styled.CameraTitle>
      <Styled.CameraImage>
        {isActive ? (
          <img src="/images/cameraBlue.png" alt="cameraBlue" width={60} height={45} />
        ) : (
          <img src="/images/cameraGrey.png" alt="cameraGrey" width={60} height={45} color="red" />
        )}
        {/* <img
          src="/images/cameraBlue.png"
          alt="cameraIcon"
          style={{ color: isActive ? 'blue' : 'red' }}
          width={60}
          height={45}
        /> */}

        {/* <img src="/images/cameraBlue.png" alt="cameraBlue" width={60} height={45} /> */}
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
