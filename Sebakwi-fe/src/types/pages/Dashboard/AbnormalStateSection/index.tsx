export interface Abnormal {
  title: string;
  count: number | undefined;
}

export interface TableProps {
  data: Wheel[] | undefined;
}

export interface Wheel {
  wheelNumber: string;
  ohtNumber: string;
  position: number;
  crack: boolean;
  stamp: boolean;
  peeling: boolean;
}

// Count 데이터 타입 정의
export interface Count {
  crack: number;
  stamp: number;
  peeling: number;
  total: number;
}

// 전체 데이터를 포함하는 인터페이스
export interface WheelData {
  count: Count;
  wheelList: Wheel[];
}

// 그래프에 다시 데이터를 요청 props
export interface AbnormalStatusSectionProps {
  onRefetch: () => void;
}
