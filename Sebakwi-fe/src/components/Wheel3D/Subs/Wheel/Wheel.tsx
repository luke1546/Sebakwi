import React from 'react';
import { useFrame, ThreeEvent } from '@react-three/fiber';
import { Text } from '@react-three/drei';
import * as THREE from 'three';
import { WheelProps } from 'types';
import { PALETTE } from 'styles';


export default function Wheel(props: WheelProps) {
  const { no, position, selected, status, sendDataToParent } = props;
  const holeSize = 0.04;
  const positions: THREE.Vector3[] = [];


  positions.push(new THREE.Vector3(0, 0, 0.3));
  positions.push(new THREE.Vector3(0, 0, -0.3));
  positions.push(new THREE.Vector3(0, 0.3, 0));
  positions.push(new THREE.Vector3(0, -0.3, 0));
  positions.push(new THREE.Vector3(0, 0.22, 0.22));
  positions.push(new THREE.Vector3(0, -0.22, 0.22));
  positions.push(new THREE.Vector3(0, 0.22, -0.22));
  positions.push(new THREE.Vector3(0, -0.22, -0.22));

  const groupRef = React.useRef<THREE.Group>(null);
  useFrame(() => {
    if (groupRef.current) {
      groupRef.current.rotation.x -= 0.05; // X축을 중심으로 회전
    }
  });

  // 클릭 이벤트 핸들러
  const handleWheelClick = (event: ThreeEvent<MouseEvent>) => {
    event.stopPropagation();
    console.log(`Wheel ${no} clicked!`);
    sendDataToParent(no - 1);
  };

  const handlePointerOver = () => {
    document.body.style.cursor = 'pointer';
    console.log(selected);
  };

  const handlePointerOut = () => {
    document.body.style.cursor = 'default';
  };

  return (
    <group position={position}>
      <group
        ref={groupRef}
        onClick={handleWheelClick}
        onPointerOver={handlePointerOver}
        onPointerOut={handlePointerOut}
      >
        {/* 바깥쪽 고무 부분 */}
        <mesh rotation={[Math.PI / 2, 0, Math.PI / 2]}
        >
          <cylinderGeometry args={[1, 1, 0.4, 32]} /> {/* 두께 조정 */}
          <meshBasicMaterial color={status === "ABNORMAL" ? `#ff5435` : no === selected ? `#3274b5` : '#ffffff'} />{' '}
          {/* 어두운 색 고무 */}
        </mesh>
        {/* 안쪽 휠 부분 */}

        <mesh rotation={[Math.PI / 2, 0, Math.PI / 2]}>
          <cylinderGeometry args={[0.7, 0.7, 0.41, 32]} /> {/* 두께 조정 */}
          <meshBasicMaterial color={status === "ABNORMAL" ? '#ff3916' : no === selected ? `#1963ac` : '#cccccc'} /> {/* 밝은 색 휠 */}
        </mesh>
        <mesh rotation={[Math.PI / 2, 0, Math.PI / 2]}>
          <cylinderGeometry args={[0.2, 0.2, 0.42, 32]} /> {/* 두께 조정 */}
          <meshBasicMaterial color={'#999999'} /> {/* 작은 고무 */}
        </mesh>
        {/* 안쪽 작은 구멍 8개 */}
        {Array.from({ length: 8 }, (_, i) => {
          return (
            <mesh key={i} position={positions[i]} rotation={[Math.PI / 2, 0, Math.PI / 2]}>
              <cylinderGeometry args={[holeSize, holeSize, 0.42, 32]} />
              <meshBasicMaterial color={'#393939'} />
            </mesh>
          );
        })}
      </group>
      <Text
        position={[0, 1.75, 0]} // 텍스트 위치 조정
        fontSize={0.8}
        color={no === selected ? `${PALETTE.MAIN_BLUE}` : `black`}
        anchorX="center"
        anchorY="middle"
      >
        {no === 1 ? 'FL' : no === 2 ? 'FR' : no === 3 ? 'RL' : no === 4 ? 'RR' : ''}
      </Text>
    </group>
  );
}
