import WheelSet from './Subs/WheelSet/WheelSet';
import { OHTWheelProps } from 'types';
import * as Styled from './Wheel3D_style';

export default function Wheel3D(props: OHTWheelProps) {
  const { selected, OHTId, status, sendDataToParent } = props;
  return (
    <Styled.WheelWrapper>
      <WheelSet
        selected={selected}
        OHTId={OHTId}
        status={status}
        sendDataToParent={sendDataToParent}
      />
    </Styled.WheelWrapper>
  );
}
