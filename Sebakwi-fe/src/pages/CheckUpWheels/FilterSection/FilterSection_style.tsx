import { BaseButton } from 'components';
import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

interface DateCheckProps {
  disabled: boolean;
}

export const Wrapper = styled.div`
  width: 100%;
  height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  @media (max-width: 1400px) {
    height: 130px;
  }
`;

export const FilterWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: start;
`;

export const FilterSearchWrapper = styled.div`
  width: 100%;
  margin-left: 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;

  @media (max-width: 1400px) {
    justify-content: start;
    flex-wrap: wrap;
    gap: 20px;
  }
`;

export const SearchWrapper = styled.div`
  height: fit-content;
  display: flex;
  justify-content: space-between;
`;

export const LeftContentWrapper = styled.div`
  display: flex;
`;

export const DatePickerContainer = styled.div`
  width: 200px;
  display: flex;
`;

export const FilterTitle = styled.p`
  margin: 0px;
  color: ${PALETTE.MAIN_BLUE};
  font-size: 17px;
  font-weight: ${Noto_Sans_KR.bold.weight};
  margin-right: 14px;
`;

export const InputWrapper = styled.input`
  width: 90%;
  height: 22px;
  display: flex;
  justify-content: center;
  align-items: center;
  border: none;
  outline: none;
  font-size: 15px;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: ${Noto_Sans_KR.medium.weight};
  ::placeholder {
    color: ${PALETTE.LIGHT_BLACK};
  }
  > p {
    margin: 0px;
    color: ${PALETTE.MAIN_BLACK};
    font-size: 16px;
  }
`;

export const RangeWrapper = styled.div<DateCheckProps>`
  height: fit-content;
  display: flex;
  align-items: center;
  margin: 0px 0px 0px 22px;

  > p {
    margin: 0px 15px;
    color: ${PALETTE.MAIN_BLACK};
    opacity: ${(props) => (props.disabled ? 0.5 : 1)};
    font-size: 20px;
  }
`;

export const OHTInputWrapper = styled.div`
  width: 134px;
  height: 26px;
  background-color: white;
  border-radius: 3px;
  border: 1px solid #0052a3;
  padding: 0px 3px 0px 3px;
  color: #1a1a1a;
  font-size: 15px;
  font-weight: 600;
`;

export const ButtonWrapper = styled.div`
  display: flex;
`;

export const ResetButton = styled(BaseButton)`
  width: 134px;
  height: 26px;
  border: 1px solid ${PALETTE.MAIN_BLUE};
  background-color: white;
`;

export const ResetButtonText = styled.p`
  margin: 0px;
  font-size: 15px;
  font-family: ${Noto_Sans_KR.bold.variable};
  font-weight: ${Noto_Sans_KR.bold.weight};
  color: ${PALETTE.MAIN_BLUE};
`;

export const SearchButton = styled(BaseButton)`
  width: 134px;
  height: 26px;
  color: ${PALETTE.MAIN_BLUE};
  margin-right: 27px;
`;

export const SearchButtonText = styled.p`
  margin: 0px;
  font-size: 15px;
  font-family: ${Noto_Sans_KR.bold.variable};
  font-weight: ${Noto_Sans_KR.bold.weight};
  color: white;
`;

export const RightContentWrapper = styled.div`
  display: flex;
  align-items: center;
`;
