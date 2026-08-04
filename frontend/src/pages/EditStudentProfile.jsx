import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import api from "../services/api";

import DashboardSidebar from "../components/DashboardSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/EditStudentProfile.css";

function EditStudentProfile() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({

        firstName: "",
        lastName: "",
        phone: "",
        profilePictureUrl: "",

        college: "",
        degree: "",
        branch: "",

        graduationYear: "",
        semester: "",
        cgpa: "",

        bio: "",
        resumeUrl: "",

        socialLinks: {

            githubUrl: "",
            linkedinUrl: "",
            portfolioUrl: ""

        }

    });

    useEffect(() => {

        fetchProfile();

    }, []);

    const fetchProfile = async () => {

        try {

            const response = await api.get("/api/students/me");

            setFormData({

                ...response.data,

                socialLinks: response.data.socialLinks || {

                    githubUrl: "",
                    linkedinUrl: "",
                    portfolioUrl: ""

                }

            });

        }

        catch (error) {

            console.error(error);

        }

    };

    const handleChange = (e) => {

        const { name, value } = e.target;

        setFormData({

            ...formData,

            [name]: value

        });

    };

    const handleSocialChange = (e) => {

        const { name, value } = e.target;

        setFormData({

            ...formData,

            socialLinks: {

                ...formData.socialLinks,

                [name]: value

            }

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const payload = {

                ...formData,

                graduationYear: Number(formData.graduationYear),

                semester: Number(formData.semester),

                cgpa: Number(formData.cgpa)

            };

            await api.put("/api/students/me", payload);

            alert("Profile Updated Successfully");

            navigate("/student/profile");

        }

        catch (error) {

            console.error(error);

            if (error.response?.data) {

                console.log(error.response.data);

            }

            alert("Unable to update profile.");

        }

    };

    return (

        <div className="dashboard-container">

            <DashboardSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <div className="edit-profile-container">

                    <h1>Edit Profile</h1>

                    <form
                        className="edit-profile-form"
                        onSubmit={handleSubmit}
                    >

                        <h2>Personal Information</h2>

                        <input
                            type="text"
                            name="firstName"
                            placeholder="First Name"
                            value={formData.firstName}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="lastName"
                            placeholder="Last Name"
                            value={formData.lastName}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="phone"
                            placeholder="Phone Number"
                            value={formData.phone}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="profilePictureUrl"
                            placeholder="Profile Picture URL"
                            value={formData.profilePictureUrl}
                            onChange={handleChange}
                        />

                        <h2>Academic Information</h2>

                        <input
                            type="text"
                            name="college"
                            placeholder="College"
                            value={formData.college}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="degree"
                            placeholder="Degree"
                            value={formData.degree}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="branch"
                            placeholder="Branch"
                            value={formData.branch}
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            name="graduationYear"
                            placeholder="Graduation Year"
                            value={formData.graduationYear}
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            name="semester"
                            placeholder="Semester"
                            value={formData.semester}
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            step="0.01"
                            name="cgpa"
                            placeholder="CGPA"
                            value={formData.cgpa}
                            onChange={handleChange}
                        />

                        <textarea
                            rows="5"
                            name="bio"
                            placeholder="Tell mentors about yourself..."
                            value={formData.bio}
                            onChange={handleChange}
                        />

                        <input
                            type="text"
                            name="resumeUrl"
                            placeholder="Resume URL"
                            value={formData.resumeUrl}
                            onChange={handleChange}
                        />

                        <h2>Social Links</h2>

                        <input
                            type="text"
                            name="githubUrl"
                            placeholder="GitHub URL"
                            value={formData.socialLinks.githubUrl}
                            onChange={handleSocialChange}
                        />

                        <input
                            type="text"
                            name="linkedinUrl"
                            placeholder="LinkedIn URL"
                            value={formData.socialLinks.linkedinUrl}
                            onChange={handleSocialChange}
                        />

                        <input
                            type="text"
                            name="portfolioUrl"
                            placeholder="Portfolio URL"
                            value={formData.socialLinks.portfolioUrl}
                            onChange={handleSocialChange}
                        />

                        <div className="profile-actions">

                            <button
                                type="button"
                                className="cancel-btn"
                                onClick={() => navigate("/student/profile")}
                            >

                                Cancel

                            </button>

                            <button
                                type="submit"
                                className="save-btn"
                            >

                                Save Changes

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default EditStudentProfile;