import { Canvas } from '@react-three/fiber';
import { OrbitControls, Text } from '@react-three/drei';
import { OHTWheelProps } from 'types';
import Wheel from './Wheel/Wheel';

export default function WheelSet(props: OHTWheelProps) {
  const { position, OHTId, serialNumbers, sendDataToParent } = props;
  return (
    <Canvas
      camera={{
        position: [0, 4.5, 4.5], // 카메라를 장면 위에 배치
        //     rotation: [Math.PI / 2, 0, 0], // 아래를 향해 내려다봄
        //     up: [0, 0, 0], // 카메라의 위쪽 방향 조정
      }}
    >
      <OrbitControls
        maxAzimuthAngle={0.5} // 최대 아지무스 각도 (우측 이동 한계)
        minAzimuthAngle={-0.5} // 최소 아지무스 각도 (좌측 이동 한계)
        maxPolarAngle={Math.PI / 2}
        minPolarAngle={0}
      />
      <directionalLight position={[5, 5, 5]} intensity={1} />
      <Text
        position={[0, 3.5, 0]} // 텍스트 위치 조정
        fontSize={0.4}
        color="black"
        anchorX="center"
        anchorY="middle"
      >
        {'OHTID :' + OHTId}
      </Text>
      <Wheel no={1} position={[-2, 0, -2]} defeat={position} serialNumbers={serialNumbers} sendDataToParent={sendDataToParent} />
      <Wheel no={2} position={[2, 0, -2]} defeat={position} serialNumbers={serialNumbers} sendDataToParent={sendDataToParent} />
      <Wheel no={3} position={[-2, 0, 2]} defeat={position} serialNumbers={serialNumbers} sendDataToParent={sendDataToParent} />
      <Wheel no={4} position={[2, 0, 2]} defeat={position} serialNumbers={serialNumbers} sendDataToParent={sendDataToParent} />
    </Canvas>
  );
}
