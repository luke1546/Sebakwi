import WheelSet from './Subs/WheelSet';
import { OHTWheelProps } from 'types';
import * as Styled from './Wheel3D_style';


export default function Wheel3D(props: OHTWheelProps) {
  const { position, OHTId, sendDataToParent, serialNumbers } = props;
  return (
    <Styled.WheelWrapper>
      <WheelSet position={position} OHTId={OHTId} sendDataToParent={sendDataToParent} serialNumbers={serialNumbers} />
    </Styled.WheelWrapper>
  );
};
