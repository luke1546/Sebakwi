import React, { useEffect, useState } from 'react';
import * as Styled from './OHTState_style';
import axios from 'axios';

interface State {
    title: string;
    count: number | undefined;
};

interface OHTCount {
    totalOht: number;
    maintenance: number;
}

function StateDetail({ title, count }: State) {
    return (
        <Styled.StatusBox>
            <Styled.StBoxTitle>{title}</Styled.StBoxTitle>
            <Styled.StBoxCount>{count}</Styled.StBoxCount>
        </Styled.StatusBox>
    );
}

export default function OHTState() {
    const [OHTData, setOHTData] = useState<OHTCount | null>(null);
    const fetchData = async () => {
        try {
            const baseUrl = process.env.REACT_APP_BASE_URL;
            const response = await axios.get<OHTCount>(`${baseUrl}/oht/replacement`);
            setOHTData(response.data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <Styled.StContainer>
            <StateDetail title="정비중" count={OHTData?.maintenance}></StateDetail>
            <StateDetail title="운행중" count={OHTData ? OHTData.totalOht - OHTData.maintenance : undefined}></StateDetail>
            <StateDetail title="총 대수" count={OHTData?.totalOht}></StateDetail>
        </Styled.StContainer>
    );
}