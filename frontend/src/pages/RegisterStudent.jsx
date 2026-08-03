import { useState } from "react";
import { useNavigate } from "react-router-dom";

import api from "../services/api";

import "../styles/RegisterStudent.css";

function RegisterStudent() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({

        username: "",
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        phone: "",

        college: "",
        degree: "",
        branch: "",
        graduationYear: "",

        semester: "",
        cgpa: "",
        bio: "",

        socialLinks: {

            githubUrl: "",
            linkedinUrl: "",
            portfolioUrl: ""

        },

        resumeUrl: ""

    });

    const handleChange = (e) => {

        const { name, value } = e.target;

        if (
            name === "githubUrl" ||
            name === "linkedinUrl" ||
            name === "portfolioUrl"
        ) {

            setFormData({

                ...formData,

                socialLinks: {

                    ...formData.socialLinks,

                    [name]: value

                }

            });

        } else {

            setFormData({

                ...formData,

                [name]: value

            });

        }

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response = await api.post(
                "/api/auth/register/student",
                formData
            );

            console.log(response.data);

            alert("Registration Successful!");

            navigate("/login");

        } catch (error) {

            console.error(error);

            alert("Registration Failed");

        }

    };

    return (

        <div className="register-page">

            <div className="register-card">

                <h1>Student Registration</h1>

                <form onSubmit={handleSubmit}>

                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="firstName"
                        placeholder="First Name"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="lastName"
                        placeholder="Last Name"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="phone"
                        placeholder="Phone Number"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="college"
                        placeholder="College"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="degree"
                        placeholder="Degree"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="text"
                        name="branch"
                        placeholder="Branch"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="number"
                        name="graduationYear"
                        placeholder="Graduation Year"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="number"
                        name="semester"
                        placeholder="Semester"
                        onChange={handleChange}
                    />

                    <input
                        type="number"
                        step="0.01"
                        name="cgpa"
                        placeholder="CGPA"
                        onChange={handleChange}
                    />

                    <textarea
                        name="bio"
                        placeholder="Bio"
                        rows="4"
                        onChange={handleChange}
                    ></textarea>

                    <input
                        type="text"
                        name="githubUrl"
                        placeholder="GitHub URL"
                        onChange={handleChange}
                    />

                    <input
                        type="text"
                        name="linkedinUrl"
                        placeholder="LinkedIn URL"
                        onChange={handleChange}
                    />

                    <input
                        type="text"
                        name="portfolioUrl"
                        placeholder="Portfolio URL"
                        onChange={handleChange}
                    />

                    <input
                        type="text"
                        name="resumeUrl"
                        placeholder="Resume URL"
                        onChange={handleChange}
                    />

                    <button type="submit">

                        Register

                    </button>

                </form>

            </div>

        </div>

    );

}

export default RegisterStudent;