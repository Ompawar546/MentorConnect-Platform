import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import api from "../services/api";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/MentorProfile.css";

function MentorProfile() {

    const { mentorUserId } = useParams();

    const [mentor, setMentor] = useState(null);

    useEffect(() => {

        fetchMentor();

    }, []);

    const fetchMentor = async () => {

        try {

            const response = await api.get(
                `/api/connections/mentor/${mentorUserId}`
            );

            setMentor(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    if (!mentor) {

        return <h2 className="loading-text">Loading...</h2>;

    }

    return (

        <div className="dashboard-container">

            <DashboardSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <div className="mentor-profile-card">

                    <div className="mentor-profile-header">

                        <div className="mentor-avatar">

                            {

                                mentor.profilePictureUrl ?

                                    (

                                        <img
                                            src={mentor.profilePictureUrl}
                                            alt="mentor"
                                        />

                                    )

                                    :

                                    (

                                        mentor.firstName?.charAt(0)

                                    )

                            }

                        </div>

                        <div>

                            <h1>

                                {mentor.firstName} {mentor.lastName}

                            </h1>

                            <h3>

                                {mentor.currentDesignation}

                            </h3>

                            <p>

                                {mentor.currentCompany}

                            </p>

                            <span
                                className={
                                    mentor.verified
                                        ? "verified"
                                        : "not-verified"
                                }
                            >

                                {

                                    mentor.verified

                                        ? "Verified Mentor"

                                        : "Not Verified"

                                }

                            </span>

                        </div>

                    </div>

                    <div className="profile-section">

                        <h2>About</h2>

                        <p>

                            {mentor.bio || "No bio added."}

                        </p>

                    </div>

                    <div className="profile-section">

                        <h2>Professional Details</h2>

                        <p>

                            <strong>Designation :</strong>

                            {" "}

                            {mentor.currentDesignation}

                        </p>

                        <p>

                            <strong>Company :</strong>

                            {" "}

                            {mentor.currentCompany}

                        </p>

                        <p>

                            <strong>Experience :</strong>

                            {" "}

                            {mentor.experienceYears} Years

                        </p>

                        <p>

                            <strong>Expertise :</strong>

                            {" "}

                            {mentor.expertise}

                        </p>

                        <p>

                            <strong>Availability :</strong>

                            {" "}

                            {mentor.availabilityStatus}

                        </p>

                        <p>

                            <strong>Rating :</strong>

                            ⭐ {mentor.averageRating}

                        </p>

                    </div>

                    <div className="profile-section">

                        <h2>Education</h2>

                        {

                            mentor.education ?

                                (

                                    <>

                                        <p>

                                            <strong>Degree :</strong>

                                            {" "}

                                            {mentor.education.degree}

                                        </p>

                                        <p>

                                            <strong>Branch :</strong>

                                            {" "}

                                            {mentor.education.branch}

                                        </p>

                                        <p>

                                            <strong>College :</strong>

                                            {" "}

                                            {mentor.education.college}

                                        </p>

                                        <p>

                                            <strong>Passing Year :</strong>

                                            {" "}

                                            {mentor.education.passingYear}

                                        </p>

                                    </>

                                )

                                :

                                (

                                    <p>No education added.</p>

                                )

                        }

                    </div>

                    <div className="profile-section">

                        <h2>Skills</h2>

                        <div className="skills-container">

                            {

                                mentor.skills?.map(skill => (

                                    <span
                                        key={skill}
                                        className="skill-chip"
                                    >

                                        {skill.replaceAll("_", " ")}

                                    </span>

                                ))

                            }

                        </div>

                    </div>

                    <div className="profile-section">

                        <h2>Previous Companies</h2>

                        {

                            mentor.previousCompanies?.length ?

                                (

                                    <ul>

                                        {

                                            mentor.previousCompanies.map(company => (

                                                <li key={company}>

                                                    {company}

                                                </li>

                                            ))

                                        }

                                    </ul>

                                )

                                :

                                (

                                    <p>No previous companies.</p>

                                )

                        }

                    </div>

                    <div className="profile-section">

                        <h2>Contact</h2>

                        <p>

                            <strong>Email :</strong>

                            {" "}

                            {mentor.email}

                        </p>

                        <p>

                            <strong>Phone :</strong>

                            {" "}

                            {mentor.phone}

                        </p>

                    </div>

                    <div className="profile-section">

                        <h2>Social Links</h2>

                        <p>

                            <strong>GitHub :</strong>

                            {" "}

                            {mentor.socialLinks?.githubUrl || "-"}

                        </p>

                        <p>

                            <strong>LinkedIn :</strong>

                            {" "}

                            {mentor.socialLinks?.linkedinUrl || "-"}

                        </p>

                        <p>

                            <strong>Portfolio :</strong>

                            {" "}

                            {mentor.socialLinks?.portfolioUrl || "-"}

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default MentorProfile;