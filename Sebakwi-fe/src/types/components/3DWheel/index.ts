import { CheckupDataProps } from "../Modal";

export interface WheelInfo {
  wheelNumber: string;
  currentStatus: string;
}

export interface CheckupData {
  checkupListId: number;
  wheelNumber: string;
  position: number;
  ohtNumber: string;
  checkedDate: string;
  wheelImage: string;
  diameter: number;
  crack: boolean;
  stamp: boolean;
  peeling: boolean;
  status: string;
  createdDate: string;
}

export interface OHTWheelProps {
  selected: number | undefined;
  OHTId: string | undefined;
  status: CheckupDataProps[] | null;
  sendDataToParent: (data: number) => void;
}

export interface WheelProps {
  no: number;
  position: [number, number, number];
  selected: number | undefined;
  status: string | null;
  sendDataToParent: (data: number) => void;
};
