import React from 'react';
import DashBoradPage from './pages/dashboard/dashboard';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Jihun from './pages/jihun/jihun';
import JiwonPage from './pages/jiwon/jiwon';
import ExamplePage from './pages/example/example';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/dashboard">대시보드</Link>
            </li>
            <li>
              <Link to="/jihun">jihun</Link>
            </li>
            <li>
              <Link to="/jiwon">jiwon</Link>
            </li>
            <li>
              <Link to="/example">Example</Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route path="/" element={<DashBoradPage />} />
          <Route path="/dashboard" element={<DashBoradPage />} />
          <Route path="/jihun" element={<Jihun />} />
          <Route path="/jiwon" element={<JiwonPage />} />
          <Route path="/example" element={<ExamplePage name="여기에 입력해" />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
