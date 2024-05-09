export interface TableSectionProps {
  Filter: {
    isCheckedDate: boolean;
    startDateTime: string;
    endDateTime: string;
    onlyAbnormal: boolean;
    position: number;
    ohtSerialNumber: string;
    WheelSerialNumber: string;
    desc: boolean;
  };
}

export interface CheckupListItem {
  checkupListId: number;
  wheelNumber: string;
  position: number;
  ohtNumber: string;
  checkedDate: string;
  status: string;
  createdDate: string;
}
