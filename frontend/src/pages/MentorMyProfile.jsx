import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import api from "../services/api";

import MentorSidebar from "../components/MentorSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/MentorProfile.css";

function MentorMyProfile() {

    const navigate = useNavigate();

    const [mentor, setMentor] = useState(null);

    useEffect(() => {

        fetchProfile();

    }, []);

    const fetchProfile = async () => {

        try {

            const response = await api.get("/api/mentors/me");

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

            <MentorSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <div className="profile-layout">

                    {/* LEFT: summary card */}
                    <aside className="profile-summary-card">

                        <div className="mentor-avatar">

                            {

                                mentor.profilePictureUrl ?

                                    (

                                        <img
                                            src={mentor.profilePictureUrl}
                                            alt="Profile"
                                        />

                                    )

                                    :

                                    (

                                        mentor.firstName?.charAt(0)

                                    )

                            }

                        </div>

                        <h1>

                            {mentor.firstName} {mentor.lastName}

                        </h1>

                        <h3>

                            {mentor.currentDesignation}

                        </h3>

                        <p className="summary-company">

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

                        <div className="summary-stats">

                            <div className="summary-stat">
                                <strong>{mentor.experienceYears}</strong>
                                <span>Years Exp.</span>
                            </div>

                            <div className="summary-stat">
                                <strong>⭐ {mentor.averageRating}</strong>
                                <span>Rating</span>
                            </div>

                            <div className="summary-stat">
                                <strong>{mentor.availabilityStatus}</strong>
                                <span>Availability</span>
                            </div>

                        </div>

                        <button
                            className="edit-profile-btn"
                            onClick={() =>
                                navigate("/mentor/profile/edit")
                            }
                        >

                            Edit Profile

                        </button>

                        <div className="summary-links">

                            <p><strong>Email :</strong> {mentor.email}</p>

                            <p><strong>Phone :</strong> {mentor.phone}</p>

                            <p><strong>Username :</strong> {mentor.username}</p>

                        </div>

                    </aside>

                    {/* RIGHT: detail sections */}
                    <div className="profile-details">

                        <div className="profile-section">

                            <h2>Education</h2>

                            {

                                mentor.education ?

                                    (

                                        <div className="detail-grid">

                                            <p><strong>Degree :</strong> {mentor.education.degree}</p>

                                            <p><strong>Branch :</strong> {mentor.education.branch}</p>

                                            <p><strong>College :</strong> {mentor.education.college}</p>

                                            <p><strong>Passing Year :</strong> {mentor.education.passingYear}</p>

                                        </div>

                                    )

                                    :

                                    (

                                        <p>No education details available.</p>

                                    )

                            }

                        </div>

                        <div className="profile-section">

                            <h2>Skills</h2>

                            <div className="skills-container">

                                {

                                    mentor.skills?.map((skill) => (

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

                                                mentor.previousCompanies.map((company) => (

                                                    <li key={company}>

                                                        {company}

                                                    </li>

                                                ))

                                            }

                                        </ul>

                                    )

                                    :

                                    (

                                        <p>No previous companies added.</p>

                                    )

                            }

                        </div>

                        <div className="profile-section">

                            <h2>Expertise</h2>

                            <p>

                                {mentor.expertise || "-"}

                            </p>

                        </div>

                        <div className="profile-section">

                            <h2>Bio</h2>

                            <p>

                                {mentor.bio || "-"}

                            </p>

                        </div>

                        <div className="profile-section">

                            <h2>Social Links</h2>

                            <p><strong>GitHub :</strong> {mentor.socialLinks?.githubUrl || "-"}</p>

                            <p><strong>LinkedIn :</strong> {mentor.socialLinks?.linkedinUrl || "-"}</p>

                            <p><strong>Portfolio :</strong> {mentor.socialLinks?.portfolioUrl || "-"}</p>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default MentorMyProfile;