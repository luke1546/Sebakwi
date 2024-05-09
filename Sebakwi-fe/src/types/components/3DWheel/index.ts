export interface ModalData {
  checkupListDetailModalWheel: CheckupData;
  checkupListDetailModalWheelNumberList: SerialNumbers;
}

export interface SerialNumbers {
  lf: string;
  lr: string;
  rf: string;
  rr: string;
}

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
  serialNumbers: SerialNumbers | null;
  sendDataToParent: (data: CheckupData) => void;
}

export interface WheelProps {
  no: number;
  position: [number, number, number];
  defeat: number | undefined;
  serialNumbers: SerialNumbers | null;
  sendDataToParent: (data: CheckupData) => void;
};
