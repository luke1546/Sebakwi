export interface BaseButtonProps {
  children: React.ReactNode;
  type: 'submit' | 'button' | 'reset';
  onClick?: (...args: any[]) => void;
  className?: string;
}


/**
 * @param children: 버튼 내부에 들어갈 요소
 * @param type: 버튼 종류
 * @param onClick: 클릭 시 할 행동 (disable true일 시 undefined)
 * @param children 들 사이에 공간이 필요한 경우
 */
//