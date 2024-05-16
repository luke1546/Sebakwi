export interface ModalProps {
  id: number | null;
  onClose: () => void;
}

export interface imageModalProps {
  show: boolean;
}

export interface CheckupDataProps {
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

export interface TableData {
  item: string | undefined;
  value: string | number | boolean | undefined;
};