import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import {
  DashBoradPage,
  CheckUpWheelsPage,
  StatisticsPage,
  JihunPage,
  JiwonPage,
  HoseongPage,
  ExamplePage,
} from 'pages';
import { Mainheader } from 'components';

function App() {
  return (
    <>
      <BrowserRouter>
        <Mainheader />
        <Routes>
          <Route path="/" element={<DashBoradPage />} />
          <Route path="/checkupwheels" element={<CheckUpWheelsPage />} />
          <Route path="/statistics" element={<StatisticsPage />} />
          <Route path="/jihun" element={<JihunPage />} />
          <Route path="/jiwon" element={<JiwonPage />} />
          <Route path="/hoseong" element={<HoseongPage />} />
          <Route path="/example" element={<ExamplePage name="props 및 typescript 예시" />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
