import React, { useState, useEffect } from 'react';
import { Slide, ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import * as Styled from './MonitoringChartSection_style';
import { ToolTips } from 'types';
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

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

export const options = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: {
      position: 'bottom' as const,
    },
  },
};

export default function MonitoringChartSection() {
  const [axisData, setAxisData] = useState<ToolTips | null>(null);
  const [times, setTimes] = useState<string[]>([]);
  // 토스트 알림 중복 방지
  // const [shownAlerts, setShownAlerts] = useState<Set<string>>(new Set());

  // 데이터를 불러오는 함수
  const fetchData = async () => {
    try {
      const baseUrl = process.env.REACT_APP_BASE_URL;
      const response = await axios.get<ToolTips>(`${baseUrl}/wheels/chart`);
      setAxisData(response.data);
      const timeOnly = response.data.xdata.map((dateTime) => {
        const timePart = dateTime.split(' ')[1]; // ' ' 공백을 기준으로 분할하여 시간 부분만 추출
        return timePart;
      });
      setTimes(timeOnly);

      // 토스트 알림 로직 부분
      // response.data.toolTips.forEach((group, index) => {
      //   group.forEach((item) => {
      //     const alertKey = `${item.wheelNumber} - ${item.crack ? 'crack' : ''}${
      //       item.stamp ? 'stamp' : ''
      //     }${item.peeling ? 'peeling' : ''}`;
      //     if (!shownAlerts.has(alertKey)) {
      //       if (item.crack) {
      //         toast.error(`${item.wheelNumber} 휠 크랙 발생`);
      //       }
      //       if (item.stamp) {
      //         toast.error(`${item.wheelNumber} 휠 찍힘 발생`);
      //       }
      //       if (item.peeling) {
      //         toast.error(`${item.wheelNumber} 휠 박리 발생`);
      //       }
      //       setShownAlerts(new Set(shownAlerts.add(alertKey)));
      //     }
      //   });
      // });
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  // 컴포넌트 마운트 시 및 30초마다 데이터 불러오기
  useEffect(() => {
    fetchData(); // 최초 로드 시 실행
    const intervalId = setInterval(fetchData, 30000); // 30초마다 실행
    return () => {
      clearInterval(intervalId);
      //   setShownAlerts(new Set()); // 인터벌이 해제될 때 알림 히스토리도 초기화
    }; // 컴포넌트 언마운트 시 인터벌 해제
  }, []);

  const xdata = [];

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
      {/* <Styled.TContainer /> */}
      {/* <ToastContainer
        position="bottom-right"
        autoClose={3000}
        hideProgressBar
        newestOnTop={false}
        closeOnClick={false}
        rtl={false}
        pauseOnFocusLoss={false}
        draggable={false}
        pauseOnHover
        theme="colored"
        transition={Slide}
      /> */}
      <Line options={options} data={data} />
    </Styled.ChartContainer>
  );
}
