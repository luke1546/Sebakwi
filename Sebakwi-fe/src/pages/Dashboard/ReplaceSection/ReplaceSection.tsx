import React, { useEffect, useState } from 'react';
import { Replace, ReplaceWheel } from 'types';
import * as Styled from './ReplaceSection_style';
import axios from 'axios';

function ReplaceComponent({ wheelNumber, date }: Replace) {
  return (
    <Styled.ReplaceWrapper>
      <Styled.WheelNumber>{wheelNumber}</Styled.WheelNumber>
      <Styled.Date>{date}</Styled.Date>
    </Styled.ReplaceWrapper>
  );
}

export default function ReplaceSection() {
  const [replaceData, setReplaceData] = useState<ReplaceWheel[] | null>(null);
  const fetchData = async () => {
    try {
      const baseUrl = process.env.REACT_APP_BASE_URL;
      const response = await axios.get<ReplaceWheel[]>(`${baseUrl}/wheels/replacement`);
      setReplaceData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <Styled.ReplaceHeader>
        <span>휠 번호</span>
        <span>교체 일자</span>
      </Styled.ReplaceHeader>
      <Styled.ReplaceContainer>
        {
          replaceData ? (
            replaceData.map((wheel, index) => (
              <ReplaceComponent
                key={index}
                wheelNumber={wheel.wheelNumber}
                date={wheel.createdDate}
              />
            ))
          ) : (
            <p>Loading...</p>
          ) // 데이터가 로드 중이거나 불러오는 데 실패한 경우
        }
      </Styled.ReplaceContainer>
    </div>
  );
}
