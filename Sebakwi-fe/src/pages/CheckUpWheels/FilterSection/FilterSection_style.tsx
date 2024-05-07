import { BaseButton } from 'components';
import styled from 'styled-components';
import { Noto_Sans_KR, PALETTE } from 'styles';

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

// Left
export const LeftContentWrapper = styled.div`
  display: flex;
`;

export const DatePickerContainer = styled.div`
  width: 136px;
  height: 26px;
  border: 1.5px solid ${PALETTE.MAIN_BLUE};
  border-radius: 5px;
  padding: 0px 10px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const FilterTitle = styled.p`
  margin: 0px;
  color: ${PALETTE.MAIN_BLUE};
  font-size: 17px;
  font-weight: ${Noto_Sans_KR.bold.weight};
  margin-right: 14px;
`;

export const DatePickerInputWrapper = styled.div`
  width: 100%;
  height: 26px;
  display: flex;
  justify-content: center;
  align-items: center;

  > p {
    margin: 0px;
    color: ${PALETTE.MAIN_BLACK};
    font-size: 16px;
  }
`;

export const RangeWrapper = styled.div`
  height: fit-content;
  display: flex;
  align-items: center;

  > p {
    margin: 0px 15px;

    color: ${PALETTE.MAIN_BLACK};
    font-size: 20px;
  }
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

// Right
export const RightContentWrapper = styled.div`
  display: flex;
  align-items: center;
`;
