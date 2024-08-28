import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import SolarTimesPage from './pages/SolarTimesPage';

const App = () => {
  return (
    <BrowserRouter>
        <Routes>
        <Route path="/registration" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/solar-watch" element={<SolarTimesPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App