import { Link } from "react-router-dom";

import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

import HeroImage from "../assets/hero.svg";

import "../styles/LandingPage.css";

function LandingPage() {
  return (
    <>
      <Navbar />

      <section className="hero">
        <div className="hero-left">
          <span className="hero-badge">★ Trusted by 500+ engineers</span>

          <h1>
            Find the Right Mentor
            <br />
            Accelerate Your Career
          </h1>

          <p>
            Connect with experienced software engineers, receive personalized
            guidance and accelerate your career growth with one-on-one
            mentorship.
          </p>

          <div className="hero-buttons">
            <Link to="/register/student" className="primary-btn">
              Find Mentors
            </Link>

            <Link to="/register/mentor" className="secondary-btn">
              Become a Mentor
            </Link>
          </div>

          <div className="hero-stats">
            <div className="stat">
              <strong>1,200+</strong>
              <span>Sessions completed</span>
            </div>
            <div className="stat">
              <strong>150+</strong>
              <span>Active mentors</span>
            </div>
            <div className="stat">
              <strong>4.9/5</strong>
              <span>Average rating</span>
            </div>
          </div>
        </div>

        <div className="hero-right">
          <div className="hero-glow" />
          <img src={HeroImage} alt="Mentor Connect" />
        </div>
      </section>

      <Footer />
    </>
  );
}

export default LandingPage;