import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import api from "../services/api";

import MentorSidebar from "../components/MentorSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/MyStudents.css";

function MyStudents() {

    const navigate = useNavigate();

    const [students, setStudents] = useState([]);

    useEffect(() => {

        fetchStudents();

    }, []);

    const fetchStudents = async () => {

        try {

            const response = await api.get("/api/connections/my-students");

            console.log(response.data);

            setStudents(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="dashboard-container">

            <MentorSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <h1>My Students</h1>

                {

                    students.length === 0 ?

                        (

                            <div className="empty-card">

                                <h3>

                                    No Students Connected Yet

                                </h3>

                                <p>

                                    Accepted students will appear here.

                                </p>

                            </div>

                        )

                        :

                        (

                            <div className="students-grid">

                                {

                                    students.map((student) => (

                                        <div
                                            key={student.id}
                                            className="student-card"
                                            onClick={() =>
                                                navigate(
                                                    `/mentor/student/${student.studentUserId}`
                                                )
                                            }
                                        >

                                            <div className="student-avatar">

                                                {

                                                    student.studentProfilePicture ?

                                                        (

                                                            <img
                                                                src={student.studentProfilePicture}
                                                                alt={student.studentName}
                                                            />

                                                        )

                                                        :

                                                        (

                                                            student.studentName
                                                                .charAt(0)
                                                                .toUpperCase()

                                                        )

                                                }

                                            </div>

                                            <h2>

                                                {student.studentName}

                                            </h2>

                                            <p>

                                                {student.studentEmail}

                                            </p>

                                        </div>

                                    ))

                                }

                            </div>

                        )

                }

            </div>

        </div>

    );

}

export default MyStudents;