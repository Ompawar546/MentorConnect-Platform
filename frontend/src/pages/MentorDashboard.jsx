import { Link } from "react-router-dom";

import MentorSidebar from "../components/MentorSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/StudentDashboard.css";

function MentorDashboard() {

    return (

        <div className="dashboard-container">

            <MentorSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <h1>Mentor Dashboard</h1>

                <div className="dashboard-cards">

                    <div className="dashboard-card">

                        <h2>Pending Requests</h2>

                        <p>

                            Review and accept student connection requests.

                        </p>

                        <Link
                            className="dashboard-btn"
                            to="/mentor/pending"
                        >

                            View Requests

                        </Link>

                    </div>

                    <div className="dashboard-card">

                        <h2>My Students</h2>

                        <p>

                            View all students connected with you.

                        </p>

                        <Link
                            className="dashboard-btn"
                            to="/mentor/students"
                        >

                            View Students

                        </Link>

                    </div>

                    <div className="dashboard-card">

                        <h2>My Profile</h2>

                        <p>

                            View and update your mentor profile.

                        </p>

                        <Link
                            className="dashboard-btn"
                            to="/mentor/profile"
                        >

                            Open Profile

                        </Link>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default MentorDashboard;