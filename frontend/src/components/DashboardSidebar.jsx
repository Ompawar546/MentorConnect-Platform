import { Link } from "react-router-dom";
import "../styles/DashboardSidebar.css";

function DashboardSidebar() {

    return (

        <div className="sidebar">

            <h2>MentorConnect</h2>

            <Link to="/student">Dashboard</Link>

            <Link to="/student/profile">My Profile</Link>

            <Link to="/student/mentors">Find Mentors</Link>

            <Link to="/student/my-mentors">My Mentors</Link>

            <button
                className="logout-btn"
                onClick={() => {

                    localStorage.clear();

                    window.location.href="/login";

                }}
            >
                Logout
            </button>

        </div>

    );

}

export default DashboardSidebar;