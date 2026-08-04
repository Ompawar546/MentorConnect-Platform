import { useEffect, useState } from "react";

import api from "../services/api";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";
import { useNavigate } from "react-router-dom";

import "../styles/StudentProfile.css";

function StudentProfile() {

    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {

        fetchProfile();

    }, []);

    const fetchProfile = async () => {

        try {

            const response = await api.get("/api/students/me");

            setStudent(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    if (!student) {

        return <h2>Loading...</h2>;

    }

    return (

        <div className="dashboard-container">

            <DashboardSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <div className="student-profile">

                    <div className="profile-header">

                        <div className="profile-avatar">

                            {student.profilePictureUrl ?

                                <img
                                    src={student.profilePictureUrl}
                                    alt=""
                                />

                                :

                                <div className="avatar-placeholder">

                                    {student.firstName.charAt(0)}

                                </div>

                            }

                        </div>

                        <div>

                            <h1>

                                {student.firstName} {student.lastName}

                            </h1>

                            <p>

                                {student.degree}

                            </p>

                            <p>

                                {student.college}

                            </p>

                        </div>

                    </div>

                    <div className="profile-card">

                        <h2>Personal Information</h2>

                        <p><strong>Email:</strong> {student.email}</p>

                        <p><strong>Phone:</strong> {student.phone}</p>

                    </div>

                    <div className="profile-card">

                        <h2>Academic Information</h2>

                        <p><strong>Branch:</strong> {student.branch}</p>

                        <p><strong>Graduation Year:</strong> {student.graduationYear}</p>

                        <p><strong>Semester:</strong> {student.semester}</p>

                        <p><strong>CGPA:</strong> {student.cgpa}</p>

                    </div>

                    <div className="profile-card">

                        <h2>About</h2>

                        <p>

                            {student.bio || "No bio added."}

                        </p>

                    </div>

                    <div className="profile-card">

                        <h2>Social Links</h2>

                        {student.socialLinks?.githubUrl && (
                            <p>
                                <a
                                    href={student.socialLinks.githubUrl}
                                    target="_blank"
                                    rel="noreferrer"
                                >
                                    GitHub
                                </a>
                            </p>
                        )}

                        {student.socialLinks?.linkedinUrl && (
                            <p>
                                <a
                                    href={student.socialLinks.linkedinUrl}
                                    target="_blank"
                                    rel="noreferrer"
                                >
                                    LinkedIn
                                </a>
                            </p>
                        )}

                        {student.socialLinks?.portfolioUrl && (
                            <p>
                                <a
                                    href={student.socialLinks.portfolioUrl}
                                    target="_blank"
                                    rel="noreferrer"
                                >
                                    Portfolio
                                </a>
                            </p>
                        )}

                        {!student.socialLinks?.githubUrl &&
                        !student.socialLinks?.linkedinUrl &&
                        !student.socialLinks?.portfolioUrl && (
                            <p>No social links added.</p>
                        )}

                    </div>

                    <div className="profile-card">

                        <h2>Resume</h2>

                        <p>

                            {student.resumeUrl || "Resume not uploaded"}

                        </p>

                    </div>

                    <button
                        className="edit-profile-btn"
                        onClick={() => navigate("/student/profile/edit")}
                    >

                        Edit Profile

                    </button>

                </div>

            </div>

        </div>

    );

}

export default StudentProfile;