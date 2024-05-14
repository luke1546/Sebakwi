const positionLabels: { [key: number]: string } = {
  1: 'FL', // Front Left
  2: 'FR', // Front Right
  3: 'RL', // Rear Left
  4: 'RR', // Rear Right
};

const dateTypes = [
  { label: '검진 날짜', value: 0 },
  { label: '교체 날짜', value: 1 },
];

const wheelPositions = [
  { label: '전체', value: 0 },
  { label: 'FL', value: 1 },
  { label: 'FR', value: 2 },
  { label: 'RL', value: 3 },
  { label: 'RR', value: 4 },
];

const sortTypes = [
  { label: '최신 순', value: 0 },
  { label: '오래된 순', value: 1 },
];

export { positionLabels, dateTypes, wheelPositions, sortTypes };
