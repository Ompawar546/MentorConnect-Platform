import { Link } from "react-router-dom";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/StudentDashboard.css";

function StudentDashboard() {

    return (

        <div className="dashboard-container">

            <DashboardSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <h1>Student Dashboard</h1>

                <div className="dashboard-cards">

                    <div className="dashboard-card">

                        <h2>Find Mentors</h2>

                        <p>
                            Explore experienced mentors and send connection requests.
                        </p>

                        <Link
                            to="/student/mentors"
                            className="dashboard-btn"
                        >
                            Explore Mentors
                        </Link>

                    </div>

                    <div className="dashboard-card">

                        <h2>My Mentors</h2>

                        <p>
                            View all your connected mentors and access their profiles.
                        </p>

                        <Link
                            to="/student/my-mentors"
                            className="dashboard-btn"
                        >
                            View Mentors
                        </Link>

                    </div>

                    <div className="dashboard-card">

                        <h2>My Profile</h2>

                        <p>
                            View and update your academic and personal profile.
                        </p>

                        <Link
                            to="/student/profile"
                            className="dashboard-btn"
                        >
                            Open Profile
                        </Link>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentDashboard;