import { useEffect, useState } from "react";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";
import MentorCard from "../components/MentorCard";

import api from "../services/api";

import "../styles/StudentDashboard.css";

function StudentDashboard() {

    const [mentors, setMentors] = useState([]);

    useEffect(() => {

        fetchMentors();

    }, []);

    const fetchMentors = async () => {

        try {

            const response = await api.get("/api/mentors");

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

                <h1>Available Mentors</h1>

                <div className="mentor-grid">

                    {mentors.map((mentor) => (

                        <MentorCard
                            key={mentor.id}
                            mentor={mentor}
                        />

                    ))}

                </div>

            </div>

        </div>

    );

}

export default StudentDashboard;