import { Link, useLocation } from "react-router-dom";
import './Navbar.css';

function Navbar() {
    const location = useLocation();

    return (
        <nav className="navbar">
            <div className="navbar-brand">
                 Foody System Microservice
            </div>
            <div className="navbar-links">
                <Link
                    to="/"
                    className={`nav-link ${location.pathname === '/' ? 'active' : ''}`}
                >
                     Restaurants
                </Link>
                <Link
                    to="/foods"
                    className={`nav-link ${location.pathname === '/foods' ? 'active' : ''}`}
                >
                    Foods
                </Link>
            </div>
        </nav>
    )
}

export default Navbar