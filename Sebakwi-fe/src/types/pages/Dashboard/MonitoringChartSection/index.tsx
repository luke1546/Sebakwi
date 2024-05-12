// 데이터 타입 정의
export interface WheelTypeData {
  wheelNumber: string;
  ohtNumber: string;
  position: number;
  crack: boolean;
  stamp: boolean;
  peeling: boolean;
}

export interface ToolTips {
  toolTips: WheelTypeData[][];
  xdata: string[];
  ydata: number[];
}

export interface MonitoringChartSectionProps {
  shouldRefetch: boolean;
}
