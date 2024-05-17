import { CameraProps } from 'types';
import * as Styled from './Camera_style';


export default function Camera(props : CameraProps) {
  const { title, isActive, onClick } = props;

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
          onChange={onClick}
        />
        <Styled.ToggleSlider />
      </Styled.ToggleSwitch>
    </Styled.CameraContainer>
  );
}
