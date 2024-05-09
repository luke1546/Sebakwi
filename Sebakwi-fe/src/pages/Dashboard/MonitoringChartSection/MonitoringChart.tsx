import React, { useState, useEffect } from 'react';
import * as Styled from './MonitoringChart_style';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import { PALETTE } from 'styles';
import axios from 'axios';

// 데이터 타입 정의
interface WheelData {
    wheelNumber: string;
    ohtNumber: string;
    position: number;
    crack: boolean;
    stamp: boolean;
    peeling: boolean;
}

interface ToolTips {
    toolTips: WheelData[][];
    xdata: string[];
    ydata: number[];
}

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);

export const options = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
        legend: {
            position: 'bottom' as const,
        },
    },
};






export default function MonitoringChart() {
    const [axisData, setAxisData] = useState<ToolTips | null>(null);
    const [times, setTimes] = useState<string[]>([]);
    // 데이터를 불러오는 함수
    const fetchData = async () => {
        try {
            const baseUrl = process.env.REACT_APP_BASE_URL;
            const response = await axios.get<ToolTips>(`${baseUrl}/wheels/chart`);
            setAxisData(response.data);
            const timeOnly = response.data.xdata.map(dateTime => {
                const timePart = dateTime.split(' ')[1]; // ' ' 공백을 기준으로 분할하여 시간 부분만 추출
                return timePart;
            });
            setTimes(timeOnly);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    // 컴포넌트 마운트 시 및 30초마다 데이터 불러오기
    useEffect(() => {
        fetchData(); // 최초 로드 시 실행
        const intervalId = setInterval(fetchData, 30000); // 30초마다 실행
        return () => clearInterval(intervalId); // 컴포넌트 언마운트 시 인터벌 해제
    }, []);

    const xdata = [

    ]

    const data = {
        labels: times,
        datasets: [
            {
                label: '이상 휠 수',
                data: axisData?.ydata,
                borderColor: `${PALETTE.MAIN_BLUE}`,
                backgroundColor: `${PALETTE.MAIN_BLUE}`,
            },
        ],
    };

    return (
        <Styled.ChartContainer>
            <Line options={options} data={data} />
        </Styled.ChartContainer>
    );
}