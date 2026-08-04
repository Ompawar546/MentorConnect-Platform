import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

import api from "../services/api";

import "../styles/LoginPage.css";

function LoginPage() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({

        email: "",
        password: ""

    });

    const handleChange = (e) => {

        setFormData({

            ...formData,
            [e.target.name]: e.target.value

        });

    };

    const handleLogin = async (e) => {

        e.preventDefault();

        try {

            const response = await api.post("/api/auth/login", formData);
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("role", response.data.role);
            localStorage.setItem("userId", response.data.userId);
            localStorage.setItem("username", response.data.username);

             if (response.data.role === "STUDENT") {

                        navigate("/student");

                    } else if (response.data.role === "MENTOR") {

                        navigate("/mentor");

                    }


            console.log(response.data);

        } catch (error) {

            console.error(error);

            alert("Invalid email or password.");

        }

    };

    return (

        <div className="login-page">

            <div className="login-card">

                <div className="login-logo">MC</div>

                <h1>Welcome Back</h1>

                <p>Login to MentorConnect</p>

                <form onSubmit={handleLogin}>

                    <div className="form-group">

                        <label>Email</label>

                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            placeholder="Enter your email"
                            required
                        />

                    </div>

                    <div className="form-group">

                        <label>Password</label>

                        <input
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder="Enter your password"
                            required
                        />

                    </div>

                    <button className="login-btn">

                        Login

                    </button>

                </form>

                <div className="divider"><span>or</span></div>

                <div className="register-links">

                    <p>Don't have an account?</p>

                    <Link to="/register/student">

                        Register as Student

                    </Link>

                    <Link to="/register/mentor">

                        Register as Mentor

                    </Link>

                </div>

            </div>

        </div>

    );

}

export default LoginPage;