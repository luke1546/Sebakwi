export interface CheckupData {
  checkedDate: string;
  crack: boolean;
  createdDate: string;
  diameter: number;
  ohtNumber: string;
  peeling: boolean;
  position: number;
  stamp: boolean;
  status: string;
  wheelImage: string;
  wheelNumber: string;
}

export interface OHTWheelProps {
  position: number | undefined;
  OHTId: string | undefined;
  sendDataToParent: (data: CheckupData) => void;
}

export interface WheelProps {
    no: number;
    position: [number, number, number];
    defeat: number | undefined;
    sendDataToParent: (data: CheckupData) => void;
  };
