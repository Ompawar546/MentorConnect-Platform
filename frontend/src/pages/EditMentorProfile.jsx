import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import api from "../services/api";

import MentorSidebar from "../components/MentorSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/EditMentorProfile.css";

const skillOptions = [
    "JAVA",
    "PYTHON",
    "JAVASCRIPT",
    "TYPESCRIPT",
    "C",
    "CPP",
    "CSHARP",
    "SPRING_BOOT",
    "SPRING_SECURITY",
    "SPRING_MVC",
    "HIBERNATE",
    "JPA",
    "NODEJS",
    "EXPRESS",
    "DJANGO",
    "FLASK",
    "DOTNET",
    "REACT",
    "ANGULAR",
    "HTML",
    "CSS",
    "BOOTSTRAP",
    "TAILWIND",
    "MYSQL",
    "POSTGRESQL",
    "MONGODB",
    "ORACLE",
    "AWS",
    "AZURE",
    "GCP",
    "DOCKER",
    "KUBERNETES",
    "JENKINS",
    "DSA",
    "OOPS",
    "DBMS",
    "OPERATING_SYSTEM",
    "COMPUTER_NETWORKS",
    "SYSTEM_DESIGN",
    "MACHINE_LEARNING",
    "DATA_SCIENCE",
    "POWER_BI",
    "EXCEL",
    "GIT",
    "GITHUB"
];

const availabilityOptions = [
    "AVAILABLE",
    "BUSY",
    "OFFLINE"
];

function EditMentorProfile() {

    const navigate = useNavigate();

    const [mentor, setMentor] = useState({

        currentCompany: "",

        previousCompanies: ["", ""],

        currentDesignation: "",

        experienceYears: "",

        education: {

            degree: "",

            branch: "",

            college: "",

            passingYear: ""

        },

        skills: [],

        expertise: "",

        bio: "",

        resumeFileUrl: "",

        employmentProofFileUrl: "",

        socialLinks: {

            githubUrl: "",

            linkedinUrl: "",

            portfolioUrl: ""

        },

        availabilityStatus: "AVAILABLE"

    });

    useEffect(() => {

        fetchProfile();

    }, []);

    const fetchProfile = async () => {

        try {

            const response = await api.get("/api/mentors/me");

            setMentor({

                ...response.data,

                previousCompanies:
                    response.data.previousCompanies?.length
                        ? response.data.previousCompanies
                        : ["", ""],

                education: response.data.education || {

                    degree: "",

                    branch: "",

                    college: "",

                    passingYear: ""

                },

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

        setMentor({

            ...mentor,

            [e.target.name]: e.target.value

        });

    };

    const handleEducation = (e) => {

        setMentor({

            ...mentor,

            education: {

                ...mentor.education,

                [e.target.name]: e.target.value

            }

        });

    };

    const handleSocial = (e) => {

        setMentor({

            ...mentor,

            socialLinks: {

                ...mentor.socialLinks,

                [e.target.name]: e.target.value

            }

        });

    };

    const handlePreviousCompany = (index, value) => {

        const companies = [...mentor.previousCompanies];

        companies[index] = value;

        setMentor({

            ...mentor,

            previousCompanies: companies

        });

    };

    const handleSkills = (e) => {

        const values = [...e.target.selectedOptions].map(
            option => option.value
        );

        if (values.length <= 6) {

            setMentor({

                ...mentor,

                skills: values

            });

        }

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const payload = {

                ...mentor,

                experienceYears: Number(mentor.experienceYears),

                education: {

                    ...mentor.education,

                    passingYear: Number(
                        mentor.education.passingYear
                    )

                }

            };

            await api.put("/api/mentors/me", payload);

            alert("Profile Updated Successfully");

            navigate("/mentor/profile");

        }

        catch (error) {

            console.error(error);

            alert("Unable to update profile.");

        }

    };

    return (

        <div className="dashboard-container">

            <MentorSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <div className="edit-profile-container">

                    <h1>Edit Mentor Profile</h1>

                    <form
                        className="edit-profile-form"
                        onSubmit={handleSubmit}
                    >

                        <h2>Professional Details</h2>

                        <input
                            name="currentCompany"
                            placeholder="Current Company"
                            value={mentor.currentCompany}
                            onChange={handleChange}
                        />

                        <input
                            name="currentDesignation"
                            placeholder="Designation"
                            value={mentor.currentDesignation}
                            onChange={handleChange}
                        />

                        <input
                            type="number"
                            name="experienceYears"
                            placeholder="Experience"
                            value={mentor.experienceYears}
                            onChange={handleChange}
                        />

                        <input
                            placeholder="Previous Company 1"
                            value={mentor.previousCompanies[0] || ""}
                            onChange={(e) =>
                                handlePreviousCompany(0, e.target.value)
                            }
                        />

                        <input
                            placeholder="Previous Company 2"
                            value={mentor.previousCompanies[1] || ""}
                            onChange={(e) =>
                                handlePreviousCompany(1, e.target.value)
                            }
                        />

                        <h2>Education</h2>

                        <input
                            name="degree"
                            placeholder="Degree"
                            value={mentor.education.degree}
                            onChange={handleEducation}
                        />

                        <input
                            name="branch"
                            placeholder="Branch"
                            value={mentor.education.branch}
                            onChange={handleEducation}
                        />

                        <input
                            name="college"
                            placeholder="College"
                            value={mentor.education.college}
                            onChange={handleEducation}
                        />

                        <input
                            type="number"
                            name="passingYear"
                            placeholder="Passing Year"
                            value={mentor.education.passingYear}
                            onChange={handleEducation}
                        />

                        <h2>Skills</h2>

                        <select
                            multiple
                            value={mentor.skills}
                            onChange={handleSkills}
                        >

                            {skillOptions.map(skill => (

                                <option
                                    key={skill}
                                    value={skill}
                                >

                                    {skill.replaceAll("_", " ")}

                                </option>

                            ))}

                        </select>

                        <h2>Expertise</h2>

                        <textarea
                            rows="3"
                            name="expertise"
                            value={mentor.expertise}
                            onChange={handleChange}
                        />

                        <h2>Bio</h2>

                        <textarea
                            rows="5"
                            name="bio"
                            value={mentor.bio}
                            onChange={handleChange}
                        />

                        <h2>Resume</h2>

                        <input
                            name="resumeFileUrl"
                            placeholder="Resume URL"
                            value={mentor.resumeFileUrl}
                            onChange={handleChange}
                        />

                        <input
                            name="employmentProofFileUrl"
                            placeholder="Employment Proof URL"
                            value={mentor.employmentProofFileUrl}
                            onChange={handleChange}
                        />

                        <h2>Social Links</h2>

                        <input
                            name="githubUrl"
                            placeholder="GitHub"
                            value={mentor.socialLinks.githubUrl}
                            onChange={handleSocial}
                        />

                        <input
                            name="linkedinUrl"
                            placeholder="LinkedIn"
                            value={mentor.socialLinks.linkedinUrl}
                            onChange={handleSocial}
                        />

                        <input
                            name="portfolioUrl"
                            placeholder="Portfolio"
                            value={mentor.socialLinks.portfolioUrl}
                            onChange={handleSocial}
                        />

                        <h2>Availability</h2>

                        <select
                            name="availabilityStatus"
                            value={mentor.availabilityStatus}
                            onChange={handleChange}
                        >

                            {availabilityOptions.map(status => (

                                <option
                                    key={status}
                                    value={status}
                                >

                                    {status}

                                </option>

                            ))}

                        </select>

                        <div className="profile-actions">

                            <button
                                type="button"
                                className="cancel-btn"
                                onClick={() =>
                                    navigate("/mentor/profile")
                                }
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

export default EditMentorProfile;