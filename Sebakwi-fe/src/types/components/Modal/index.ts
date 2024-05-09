export interface ModalProps {
  id: number | null;
  onClose: () => void;
}

export interface CheckupDataProps {
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

export interface TableData {
  item: string | undefined;
  value: string | number | boolean | undefined;
};