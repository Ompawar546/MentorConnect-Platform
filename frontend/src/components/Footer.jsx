import { Link } from "react-router-dom";
import "../styles/Footer.css";

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-top">
        <div className="footer-brand">
          <h3>MentorConnect</h3>
          <p>
            Connecting ambitious engineers with mentors who've been there —
            one conversation at a time.
          </p>
          <div className="footer-socials">
            <a href="https://twitter.com" target="_blank" rel="noreferrer">Twitter</a>
            <a href="https://linkedin.com" target="_blank" rel="noreferrer">LinkedIn</a>
            <a href="https://github.com" target="_blank" rel="noreferrer">GitHub</a>
          </div>
        </div>

        <div className="footer-col">
          <h4>Platform</h4>
          <Link to="/register/student">Find Mentors</Link>
          <Link to="/register/mentor">Become a Mentor</Link>
          <Link to="/how-it-works">How It Works</Link>
        </div>

        <div className="footer-col">
          <h4>Company</h4>
          <Link to="/about">About Us</Link>
          <Link to="/careers">Careers</Link>
          <Link to="/contact">Contact</Link>
        </div>

        <div className="footer-col">
          <h4>Legal</h4>
          <Link to="/privacy">Privacy Policy</Link>
          <Link to="/terms">Terms of Service</Link>
        </div>
      </div>

      <div className="footer-bottom">
        <p>© 2026 MentorConnect. All Rights Reserved.</p>
      </div>
    </footer>
  );
}

export default Footer;