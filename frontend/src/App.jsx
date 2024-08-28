import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import SolarTimesPage from './pages/SolarTimesPage';
import CitiesPage from './pages/CitiesPage';
import CityUpdatePAge from './pages/CityUpdatePAge';
import SunriseSunsetPage from './pages/SunriseSunsetPage';

const App = () => {
  return (
    <BrowserRouter>
        <Routes>
        <Route path="/registration" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/solar-watch" element={<SolarTimesPage />} />
        <Route path="/cities" element={<CitiesPage />} />
        <Route path="/cities/:id/edit" element={<CityUpdatePAge />} />
        <Route path="/cities/:id/sunrise-sunset" element={<SunriseSunsetPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App