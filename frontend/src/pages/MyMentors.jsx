import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import api from "../services/api";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/StudentDashboard.css";

function MyMentors() {

    const [mentors, setMentors] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {

        fetchMyMentors();

    }, []);

    const fetchMyMentors = async () => {

        try {

            const response = await api.get("/api/connections/my-mentors");

            console.log(response.data);

            setMentors(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="dashboard-container">

            <DashboardSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <h1>My Mentors</h1>

                {
                    mentors.length === 0 ? (

                        <p>No mentors connected yet.</p>

                    ) : (

                        <div className="mentors-grid">

                            {mentors.map((mentor) => (

                                <div
                                    key={mentor.id}
                                    className="mentor-card"
                                    onClick={() =>
                                        navigate(`/student/mentor/${mentor.mentorUserId}`)
                                    }
                                    style={{ cursor: "pointer" }}
                                >

                                    <div className="mentor-avatar">

                                        {mentor.mentorProfilePicture ? (

                                            <img
                                                src={mentor.mentorProfilePicture}
                                                alt={mentor.mentorName}
                                            />

                                        ) : (

                                            <span>
                                                {mentor.mentorName.charAt(0)}
                                            </span>

                                        )}

                                    </div>

                                    <h3>

                                        {mentor.mentorName}

                                    </h3>

                                    <p>

                                        {mentor.mentorEmail}

                                    </p>

                                    <p>

                                        {mentor.mentorCompany}

                                    </p>

                                    <p>

                                        {mentor.mentorDesignation}

                                    </p>

                                    <p>

                                        {mentor.mentorExperienceYears} Years Experience

                                    </p>

                                </div>

                            ))}

                        </div>

                    )
                }

            </div>

        </div>

    );

}

export default MyMentors;