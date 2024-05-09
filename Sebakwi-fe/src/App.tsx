import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import {
  DashBoradPage,
  CheckUpWheelsPage,
  StatisticsPage,
} from 'pages';
import { MainHeader } from 'components';

function App() {
  return (
    <>
        <BrowserRouter>
          <MainHeader />
          <Routes>
            <Route path="/" element={<DashBoradPage />} />
            <Route path="/checkupwheels" element={<CheckUpWheelsPage />} />
            <Route path="/statistics" element={<StatisticsPage />} />
          </Routes>
        </BrowserRouter>
    </>
  );
}

export default App;
