import { Routes, Route } from "react-router-dom";

import LandingPage from "./pages/LandingPage";
import LoginPage from "./pages/LoginPage";

import RegisterStudent from "./pages/RegisterStudent";
import RegisterMentor from "./pages/RegisterMentor";

import StudentDashboard from "./pages/StudentDashboard";
import FindMentors from "./pages/FindMentors";
import StudentProfile from "./pages/StudentProfile";
import EditStudentProfile from "./pages/EditStudentProfile";
import MyMentors from "./pages/MyMentors";
import MentorProfile from "./pages/MentorProfile";

import MentorDashboard from "./pages/MentorDashboard";
import MentorMyProfile from "./pages/MentorMyProfile";
import EditMentorProfile from "./pages/EditMentorProfile";
import PendingRequests from "./pages/PendingRequests";
import MyStudents from "./pages/MyStudents";
import StudentViewProfile from "./pages/StudentViewProfile";





function App() {

    return (

        <Routes>

            {/* Landing */}

            <Route
                path="/"
                element={<LandingPage />}
            />

            <Route
                path="/login"
                element={<LoginPage />}
            />

            {/* Registration */}

            <Route
                path="/register/student"
                element={<RegisterStudent />}
            />

            <Route
                path="/register/mentor"
                element={<RegisterMentor />}
            />

            {/* ===========================
                    STUDENT MODULE
               =========================== */}

            <Route
                path="/student"
                element={<StudentDashboard />}
            />

            <Route
                path="/student/mentors"
                element={<FindMentors />}
            />

            <Route
                path="/student/profile"
                element={<StudentProfile />}
            />

            <Route
                path="/student/profile/edit"
                element={<EditStudentProfile />}
            />

            <Route
                path="/student/my-mentors"
                element={<MyMentors />}
            />

            <Route
                path="/student/mentor/:mentorUserId"
                element={<MentorProfile />}
            />

            {/* ===========================
                    MENTOR MODULE
               =========================== */}

            <Route
                path="/mentor"
                element={<MentorDashboard />}
            />

            <Route
                path="/mentor/profile"
                element={<MentorMyProfile />}
            />

            <Route
                path="/mentor/profile/edit"
                element={<EditMentorProfile />}
            />

            <Route
                path="/mentor/pending"
                element={<PendingRequests />}
            />

            <Route
                path="/mentor/students"
                element={<MyStudents />}
            />

            <Route
                path="/mentor/student/:studentUserId"
                element={<StudentViewProfile />}
            />

        </Routes>

    );

}

export default App;