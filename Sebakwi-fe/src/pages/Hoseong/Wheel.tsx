import React from 'react';
import { Canvas, useFrame } from '@react-three/fiber';
import { OrbitControls, Text } from '@react-three/drei';
import * as THREE from 'three';
import * as Styled from "./Wheel_style";

const Wheel = ({ no, position, label }: { no: number, position: [number, number, number]; label: string }) => {
    const meshRef = React.useRef<THREE.Mesh>(null);
    const holeSize = 0.04;
    const positions: THREE.Vector3[] = [];
    // positions.push(new THREE.Vector3(0, 0.3, 0));
    // positions.push(new THREE.Vector3(0, -0.3, 0));
    // positions.push(new THREE.Vector3(0.3, 0, 0));
    // positions.push(new THREE.Vector3(-0.3, 0, 0));
    // positions.push(new THREE.Vector3(0.22, 0.22, 0));
    // positions.push(new THREE.Vector3(0.22, -0.22, 0));
    // positions.push(new THREE.Vector3(-0.22, 0.22, 0));
    // positions.push(new THREE.Vector3(-0.22, -0.22, 0));

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

    return (
        <group position={position}>
            <group ref={groupRef} >
                {/* 바깥쪽 고무 부분 */}
                <mesh rotation={[Math.PI / 2, 0, Math.PI / 2]}>
                    <cylinderGeometry args={[1, 1, 0.4, 32]} /> {/* 두께 조정 */}
                    <meshStandardMaterial color={no === 3 ? "#de2e2e" : "#ffffff"} /> {/* 어두운 색 고무 */}
                </mesh>

                {/* 안쪽 휠 부분 */}
                <mesh rotation={[Math.PI / 2, 0, Math.PI / 2]}>
                    <cylinderGeometry args={[0.7, 0.7, 0.41, 32]} /> {/* 두께 조정 */}
                    <meshStandardMaterial color={no === 3 ? "#ad3333" : "#cccccc"} /> {/* 밝은 색 휠 */}
                </mesh>

                <mesh rotation={[Math.PI / 2, 0, Math.PI / 2]}>
                    <cylinderGeometry args={[0.2, 0.2, 0.42, 32]} /> {/* 두께 조정 */}
                    <meshStandardMaterial color={"#999999"} /> {/* 작은 고무 */}
                </mesh>

                {/* 안쪽 작은 구멍 8개 */}
                {Array.from({ length: 8 }, (_, i) => {
                    return (
                        <mesh key={i} position={positions[i]} rotation={[Math.PI / 2, 0, Math.PI / 2]}>
                            <cylinderGeometry args={[holeSize, holeSize, 0.42, 32]} />
                            <meshStandardMaterial color={"#393939"} />
                        </mesh>
                    );
                })}
            </group>
            <Text
                position={[0, 1.75, 0]} // 텍스트 위치 조정
                fontSize={1}
                color="black"
                anchorX="center"
                anchorY="middle"
            >
                {label}
            </Text>

        </group>

    );
};

const WheelSet = () => {
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
            <Wheel no={1} position={[-2, 0, -2]} label="1" />
            <Wheel no={2} position={[2, 0, -2]} label="2" />
            <Wheel no={3} position={[-2, 0, 2]} label="3" /> {/* 결함이 있는 바퀴 */}
            <Wheel no={4} position={[2, 0, 2]} label="4" />
        </Canvas>
    );
};

const OHTWheel: React.FC = () => {
    return (
        <Styled.WheelWrapper>
            <WheelSet />
        </Styled.WheelWrapper>
    );
};

export default OHTWheel;