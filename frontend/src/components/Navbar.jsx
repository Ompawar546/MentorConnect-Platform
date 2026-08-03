import { Link } from "react-router-dom";

import "../styles/Navbar.css";

function Navbar() {
  return (
    <nav className="navbar">
      <Link to="/" className="logo">
        MentorConnect
      </Link>

      <div className="nav-links">
        <Link to="/">Home</Link>

        <Link to="/login">Login</Link>

        <Link to="/register/student">Student</Link>

        <Link to="/register/mentor">Mentor</Link>
      </div>
    </nav>
  );
}

export default Navbar;