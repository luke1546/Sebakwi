import React from 'react';
import { Canvas, useFrame } from '@react-three/fiber';
import { OrbitControls } from '@react-three/drei';
import * as THREE from 'three';
import styled from 'styled-components';

const WheelWrapper = styled.div`
  width: 20vw;
  height: 40vh;
`;

const Wheel = ({ color, position }: { color: string; position: [number, number, number] }) => {
    const meshRef = React.useRef<THREE.Mesh>(null);

    return (
        <mesh ref={meshRef} position={position} rotation={[Math.PI / 2, 0, Math.PI / 2]}>
            <cylinderGeometry args={[1, 1, 0.5, 32]} />
            <meshStandardMaterial color={color} />
        </mesh>
    );
};

const WheelSet = () => {
    return (
        <Canvas>
            <OrbitControls
                maxAzimuthAngle={0.1} // 최대 아지무스 각도 (우측 이동 한계)
                minAzimuthAngle={-0.1} // 최소 아지무스 각도 (좌측 이동 한계)
                maxPolarAngle={Math.PI / 4}
                minPolarAngle={0}
            />
            <directionalLight position={[5, 5, 5]} intensity={1} />
            <Wheel color="#888888" position={[-2, 0, -2]} />
            <Wheel color="#888888" position={[2, 0, -2]} />
            <Wheel color="#ff0000" position={[-2, 0, 2]} /> {/* 결함이 있는 바퀴 */}
            <Wheel color="#888888" position={[2, 0, 2]} />
        </Canvas>
    );
};

const OHTWheel: React.FC = () => {
    return (
        <WheelWrapper>
            <WheelSet />
        </WheelWrapper>
    );
};

export default OHTWheel;