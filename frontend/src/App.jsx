import { Routes, Route } from "react-router-dom";

import LandingPage from "./pages/LandingPage";
import LoginPage from "./pages/LoginPage";
import RegisterStudent from "./pages/RegisterStudent";
import RegisterMentor from "./pages/RegisterMentor";
import StudentDashboard from "./pages/StudentDashboard";
import MentorDashboard from "./pages/MentorDashboard";

function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register/student" element={<RegisterStudent />} />
      <Route path="/register/mentor" element={<RegisterMentor />} />
      <Route path="/student" element={<StudentDashboard />} />
      <Route path="/mentor" element={<MentorDashboard />} />
    </Routes>
  );
}

export default App;