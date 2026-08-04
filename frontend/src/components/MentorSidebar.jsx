import { Link } from "react-router-dom";
import "../styles/DashboardSidebar.css";

function MentorSidebar() {

    return (

        <div className="sidebar">

            <h2>MentorConnect</h2>

            <Link to="/mentor">
                Dashboard
            </Link>

            <Link to="/mentor/profile">
                My Profile
            </Link>

            <Link to="/mentor/pending">
                Pending Requests
            </Link>

            <Link to="/mentor/students">
                My Students
            </Link>

            <button
                className="logout-btn"
                onClick={() => {

                    localStorage.clear();
                    window.location.href = "/login";

                }}
            >
                Logout
            </button>

        </div>

    );

}

export default MentorSidebar;