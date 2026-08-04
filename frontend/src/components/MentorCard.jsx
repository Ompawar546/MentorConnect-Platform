import { useState } from "react";
import api from "../services/api";

import "../styles/MentorCard.css";

function MentorCard({ mentor }) {

    const [loading, setLoading] = useState(false);
    const [requested, setRequested] = useState(false);

    const sendRequest = async () => {

        try {

            setLoading(true);

            await api.post(`/api/connections/request/${mentor.id}`);

            setRequested(true);

        }
        catch (error) {

            console.error(error);

            if (error.response?.data) {

                alert(error.response.data);

            }
            else {

                alert("Unable to send request");

            }

        }
        finally {

            setLoading(false);

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
                disabled={loading || requested}
            >

                {loading
                    ? "Sending..."
                    : requested
                        ? "Pending"
                        : "Connect"}

            </button>

        </div>

    );

}

export default MentorCard;