import { Route, Router, Routes } from "react-router"
import RegisterPage from "./pages/RegisterPage"
import LoginPage from "./pages/LoginPage"

function App() {
  return (
    <Router>
        <Routes>
            <Route path="/registration" element={<RegisterPage />} />
            <Route path="/login" element={<LoginPage />} />
        </Routes>
    </Router>
  )
}

export default App