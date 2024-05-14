import React, { useState, useEffect } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import * as Styled from './MonitoringChartSection_style';
import { ToolTips, MonitoringChartSectionProps } from 'types';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  TooltipItem,
} from 'chart.js';
import { Line } from 'react-chartjs-2';
import { PALETTE } from 'styles';
import axios from 'axios';

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);


export default function MonitoringChartSection({ shouldRefetch }: MonitoringChartSectionProps) {
  const [axiosData, setAxiosData] = useState<ToolTips | null>(null);
  const [times, setTimes] = useState<string[]>([]);

  // 데이터를 불러오는 함수
  async function fetchData () {
    try {
      const baseUrl = process.env.REACT_APP_BASE_URL;
      const response = await axios.get<ToolTips>(`${baseUrl}/wheels/chart`);
      const timeOnly = response.data.xdata.map((dateTime) => {
        const timePart = dateTime.split(' ')[1]; // ' ' 공백을 기준으로 분할하여 시간 부분만 추출
        return timePart;
      });
      setAxiosData(response.data);
      setTimes(timeOnly);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  // test
  useEffect(() => {
    fetchData(); // 최초 로드 시 실행

    let intervalId: NodeJS.Timeout | null = null;

    // 현재 시간을 기준으로 다음 00초 또는 30초까지의 시간 계산
    const setupTimer = () => {
      const now = new Date();
      // 타이머(밀리초 변환, 현재 시간부터 다음 00초 또는 30초까지의 남은 시간 계산)
      const delay = 30000 - ((now.getSeconds() * 1000 + now.getMilliseconds()) % 30000); // 타이머

      if (intervalId) clearInterval(intervalId); // 기존 인터벌 정리

      intervalId = setTimeout(() => {
        fetchData();
        intervalId = setInterval(fetchData, 30000); // 30초마다 실행
      }, delay); // delay 시간 후, 실행
    };

    setupTimer();

    return () => {
      if (intervalId) clearInterval(intervalId); // 컴포넌트 언마운트 시 인터벌 해제
    };
  }, [shouldRefetch]); // shouldRefetch가 변할때마다 렌더링

  const data = {
    labels: times, // x축 label
    datasets: [
      {
        label: '이상 휠 수', // y축
        data: axiosData?.ydata,
        borderColor: `${PALETTE.MAIN_BLUE}`,
        backgroundColor: `${PALETTE.MAIN_BLUE}`,
      },
    ],
  };

  const options = {
    responsive: true, // 반응형 설정
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom' as const,
      },
      tooltip: {
        callbacks: {
          label: (context: TooltipItem<'line'>) => {
            const dataIndex = context.dataIndex;
            const toolTips = axiosData?.toolTips[dataIndex];

            if (toolTips && toolTips.length > 0) {
              const toolTipsLabels = toolTips.map((toolTip) => {
                const { wheelNumber, crack, stamp, peeling } = toolTip;

                let status = [];
                if (stamp) status.push('찍힘');
                if (crack) status.push('크랙');
                if (peeling) status.push('박리');

                let statusText = status.join(', ');

                return `휠 넘버: ${wheelNumber}  ${statusText}`;
              });

              return toolTipsLabels;
            }

            return context.dataset.label || '';
          },
        },
      },
    },
    scales: {
      y: {
        beginAtZero: true, // Y축의 시작을 0으로
        suggestedMax: 2,
        ticks: {
          maxTicksLimit: 4,
        },
      },
    },
  };

  return (
    <Styled.ChartContainer>
      <Line options={options} data={data} />
    </Styled.ChartContainer>
  );
}


