import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import api from "../services/api";

import MentorSidebar from "../components/MentorSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/StudentViewProfile.css";

function StudentViewProfile() {

    const { studentUserId } = useParams();

    const [student, setStudent] = useState(null);

    useEffect(() => {

        fetchStudent();

    }, []);

    const fetchStudent = async () => {

        try {

            const response = await api.get(
                `/api/students/${studentUserId}`
            );

            console.log(response.data);

            setStudent(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    if (!student) {

        return <h2 className="loading-text">Loading...</h2>;

    }

    return (

        <div className="dashboard-container">

            <MentorSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <div className="student-profile-card">

                    <div className="student-profile-header">

                        <div className="student-avatar">

                            {

                                student.profilePictureUrl ?

                                    (

                                        <img
                                            src={student.profilePictureUrl}
                                            alt="Student"
                                        />

                                    )

                                    :

                                    (

                                        student.firstName.charAt(0)

                                    )

                            }

                        </div>

                        <div>

                            <h1>

                                {student.firstName} {student.lastName}

                            </h1>

                            <p>

                                {student.email}

                            </p>

                            <p>

                                {student.phone}

                            </p>

                        </div>

                    </div>

                    <div className="profile-section">

                        <h2>Academic Information</h2>

                        <p><strong>College :</strong> {student.college}</p>

                        <p><strong>Degree :</strong> {student.degree}</p>

                        <p><strong>Branch :</strong> {student.branch}</p>

                        <p><strong>Graduation Year :</strong> {student.graduationYear}</p>

                        <p><strong>Semester :</strong> {student.semester}</p>

                        <p><strong>CGPA :</strong> {student.cgpa}</p>

                    </div>

                    <div className="profile-section">

                        <h2>Bio</h2>

                        <p>

                            {student.bio || "No bio added."}

                        </p>

                    </div>

                    <div className="profile-section">

                        <h2>Resume</h2>

                        {

                            student.resumeUrl ?

                                (

                                    <a
                                        href={student.resumeUrl}
                                        target="_blank"
                                        rel="noreferrer"
                                    >

                                        View Resume

                                    </a>

                                )

                                :

                                (

                                    <p>No resume uploaded.</p>

                                )

                        }

                    </div>

                    <div className="profile-section">

                        <h2>Social Links</h2>

                        <p>

                            <strong>GitHub :</strong>

                            {" "}

                            {student.socialLinks?.githubUrl || "-"}

                        </p>

                        <p>

                            <strong>LinkedIn :</strong>

                            {" "}

                            {student.socialLinks?.linkedinUrl || "-"}

                        </p>

                        <p>

                            <strong>Portfolio :</strong>

                            {" "}

                            {student.socialLinks?.portfolioUrl || "-"}

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentViewProfile;