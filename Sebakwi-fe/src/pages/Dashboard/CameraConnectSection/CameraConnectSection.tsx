import Camera from 'components/Camera/Camera';
import * as Styled from './CameraConnectSection_style';
import { PALETTE } from 'styles';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { cameras } from 'constant';


export default function CameraConnectSection() {
  const [powerData, setPowerData] = useState<boolean[]>([true, true, true, true]);

  useEffect(() => {
    async function fetchData() {
      try {
        const baseUrl = process.env.REACT_APP_BASE_URL;
        const response = await axios.get(`${baseUrl}/jetson/status`);
        console.log(response.data);
        setPowerData(response.data);
      } catch (error) {
        // console.error('Error : ', error);
      }
    }

    fetchData();
  }, []);


  async function togglePower (index: number) {
    try {
      const baseUrl = process.env.REACT_APP_BASE_URL;
      await axios.post(`${baseUrl}/jetson/button`, { camera: index });

      setPowerData((prevPowerData) =>
        prevPowerData.map((power, i) => (i === index ? !power : power))
      );
    } catch (error) {
      console.error('Failed to toggle power', error);
    }
  };


  return (
    <Styled.Container>
      {cameras.map((camera, index) => {
        return (
          <Styled.CameraWraper id={camera} key={camera}>
            <Camera title={camera} isActive={powerData[index]} onClick={() => togglePower(index) }/>
          </Styled.CameraWraper>
        );
      })}
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
