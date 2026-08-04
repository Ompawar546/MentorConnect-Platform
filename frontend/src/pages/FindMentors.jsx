import { useEffect, useState } from "react";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";
import MentorCard from "../components/MentorCard";
import MentorSearchBar from "../components/MentorSearchBar";

import api from "../services/api";

import "../styles/FindMentors.css";

function FindMentors() {

    const [mentors, setMentors] = useState([]);

    useEffect(() => {

        fetchMentors();

    }, []);

    const fetchMentors = async () => {

        try {

            const response = await api.get("/api/mentors");

            setMentors(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    const handleSearch = async (filters) => {

        try {

            // No filters -> Load all mentors
            if (

                !filters.company &&
                !filters.skill &&
                !filters.experienceYears &&
                !filters.verified

            ) {

                fetchMentors();

                return;

            }

            const params = {};

            if (filters.company) {

                params.company = filters.company;

            }

            if (filters.skill) {

                params.skill = filters.skill;

            }

            if (filters.experienceYears) {

                params.experienceYears = filters.experienceYears;

            }

            if (filters.verified) {

                params.verified = true;

            }

            const response = await api.get(

                "/api/mentors/search",

                {

                    params

                }

            );

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

                <h1>Find Mentors</h1>

                <MentorSearchBar
                    onSearch={handleSearch}
                />

                {

                    mentors.length === 0 ?

                        (

                            <p>

                                No mentors found.

                            </p>

                        )

                        :

                        (

                            <div className="mentor-grid">

                                {

                                    mentors.map((mentor) => (

                                        <MentorCard
                                            key={mentor.id}
                                            mentor={mentor}
                                        />

                                    ))

                                }

                            </div>

                        )

                }

            </div>

        </div>

    );

}

export default FindMentors;