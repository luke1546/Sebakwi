import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import {
  DashBoradPage,
  CheckUpWheelsPage,
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
          </Routes>
        </BrowserRouter>
    </>
  );
}

export default App;
