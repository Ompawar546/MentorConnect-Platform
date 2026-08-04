import "../styles/DashboardNavbar.css";

function DashboardNavbar() {

    return (

        <div className="dashboard-navbar">

            <h2>
                Welcome, {localStorage.getItem("username")}
            </h2>

        </div>

    );

}

export default DashboardNavbar;