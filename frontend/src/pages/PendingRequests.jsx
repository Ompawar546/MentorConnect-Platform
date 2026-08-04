import { useEffect, useState } from "react";

import api from "../services/api";

import MentorSidebar from "../components/MentorSidebar";
import DashboardNavbar from "../components/DashboardNavbar";

import "../styles/StudentDashboard.css";
import "../styles/PendingRequests.css";

function PendingRequests() {

    const [requests, setRequests] = useState([]);

    useEffect(() => {

        fetchPendingRequests();

    }, []);

    const fetchPendingRequests = async () => {

        try {

            const response = await api.get("/api/connections/pending");

            console.log(response.data);

            setRequests(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    const acceptRequest = async (requestId) => {

        try {

            await api.put(`/api/connections/${requestId}/accept`);

            setRequests((prev) =>
                prev.filter((request) => request.id !== requestId)
            );

            alert("Request Accepted");

        }

        catch (error) {

            console.error(error);

            alert("Unable to accept request.");

        }

    };

    const rejectRequest = async (requestId) => {

        try {

            await api.put(`/api/connections/${requestId}/reject`);

            setRequests((prev) =>
                prev.filter((request) => request.id !== requestId)
            );

            alert("Request Rejected");

        }

        catch (error) {

            console.error(error);

            alert("Unable to reject request.");

        }

    };

    return (

        <div className="dashboard-container">

            <MentorSidebar />

            <div className="dashboard-content">

                <DashboardNavbar />

                <h1>Pending Connection Requests</h1>

                {

                    requests.length === 0 ?

                        (

                            <div className="no-pending">

                                No pending connection requests.

                            </div>

                        )

                        :

                        (

                            requests.map((request) => (

                                <div
                                    key={request.id}
                                    className="pending-request-card"
                                >

                                    <div className="pending-request-header">

                                        <div className="pending-avatar">

                                            {

                                                request.studentProfilePicture ?

                                                    (

                                                        <img
                                                            src={request.studentProfilePicture}
                                                            alt={request.studentName}
                                                        />

                                                    )

                                                    :

                                                    (

                                                        <span>

                                                            {request.studentName.charAt(0)}

                                                        </span>

                                                    )

                                            }

                                        </div>

                                        <div className="pending-info">

                                            <h3>

                                                {request.studentName}

                                            </h3>

                                            <p>

                                                {request.studentEmail}

                                            </p>

                                        </div>

                                    </div>

                                    <div className="pending-actions">

                                        <button
                                            className="accept-btn"
                                            onClick={() => acceptRequest(request.id)}
                                        >

                                            Accept

                                        </button>

                                        <button
                                            className="reject-btn"
                                            onClick={() => rejectRequest(request.id)}
                                        >

                                            Reject

                                        </button>

                                    </div>

                                </div>

                            ))

                        )

                }

            </div>

        </div>

    );

}

export default PendingRequests;