import api from "../services/api";

import "../styles/MentorCard.css";

function MentorCard({ mentor }) {

    const sendRequest = async () => {

        try {

            await api.post(`/api/connections/request/${mentor.id}`);

            alert("Connection Request Sent");

        }

        catch (error) {

            console.error(error);

            alert("Unable to send request");

        }

    };

    return (

        <div className="mentor-card">

            <div className="mentor-avatar">

                👨‍💻

            </div>

            <h3>

                {mentor.currentCompany}

            </h3>

            <p>

                {mentor.currentDesignation}

            </p>

            <p>

                {mentor.experienceYears} Years Experience

            </p>

            <div className="skills">

                {mentor.skills?.map((skill) => (

                    <span key={skill}>

                        {skill}

                    </span>

                ))}

            </div>

            <p>

                ⭐ {mentor.averageRating}

            </p>

            {mentor.verified && (

                <p className="verified">

                    ✔ Verified Mentor

                </p>

            )}

            <button
                onClick={sendRequest}
            >

                Connect

            </button>

        </div>

    );

}

export default MentorCard;