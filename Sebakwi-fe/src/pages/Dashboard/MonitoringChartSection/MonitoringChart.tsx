import React from 'react';
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

        // title: {
        //     display: true,
        //     text: 'Chart.js Line Chart',
        // },
    },
};

const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

export const data = {
    labels,
    datasets: [
        {
            label: '이상 휠 수',
            data: [0, 1, 2, 1, 2, 0, 1],
            borderColor: `${PALETTE.MAIN_BLUE}`,
            backgroundColor: `${PALETTE.MAIN_BLUE}`,
        },
    ],
};

export default function MonitoringChart() {
    return (
        <Styled.ChartContainer>
            <Line options={options} data={data} />
        </Styled.ChartContainer>
    );
}