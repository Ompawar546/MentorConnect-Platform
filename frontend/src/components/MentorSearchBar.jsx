import { useState } from "react";

import "../styles/MentorSearchBar.css";

const skills = [

    "",
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

function MentorSearchBar({ onSearch }) {

    const [company, setCompany] = useState("");

    const [skill, setSkill] = useState("");

    const [experienceYears, setExperienceYears] = useState("");

    const [verified, setVerified] = useState(false);

    const handleSearch = () => {

        onSearch({

            company,

            skill,

            experienceYears,

            verified

        });

    };

    const handleReset = () => {

        setCompany("");

        setSkill("");

        setExperienceYears("");

        setVerified(false);

        onSearch({});

    };

    return (

        <div className="mentor-search">

            <input
                type="text"
                placeholder="Search Company..."
                value={company}
                onChange={(e) => setCompany(e.target.value)}
            />

            <select
                value={skill}
                onChange={(e) => setSkill(e.target.value)}
            >

                <option value="">All Skills</option>

                {skills.slice(1).map((item) => (

                    <option
                        key={item}
                        value={item}
                    >

                        {item.replaceAll("_", " ")}

                    </option>

                ))}

            </select>

            <select
                value={experienceYears}
                onChange={(e) => setExperienceYears(e.target.value)}
            >

                <option value="">Experience</option>

                <option value="1">1+ Years</option>

                <option value="3">3+ Years</option>

                <option value="5">5+ Years</option>

                <option value="10">10+ Years</option>

            </select>

            <label>

                <input
                    type="checkbox"
                    checked={verified}
                    onChange={(e) => setVerified(e.target.checked)}
                />

                Verified

            </label>

            <button onClick={handleSearch}>

                Search

            </button>

            <button
                className="reset-btn"
                onClick={handleReset}
            >

                Reset

            </button>

        </div>

    );

}

export default MentorSearchBar;