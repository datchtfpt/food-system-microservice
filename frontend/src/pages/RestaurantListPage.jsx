import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axiosConfig.js";
import '../pages.css';

function RestaurantListPage() {

    const navigate = useNavigate()
    const [restaurants, setRestaurants] = useState([])
    const [error, setError] = useState(null)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        api.get('/restaurant-service/api/restaurants')
            .then(response => {
                setRestaurants(response.data.data.content)
            })
            .catch(err => {
                setError(err.message)
            })
            .finally(() => {
                setLoading(false);
            })
    }, []);

    if (loading) return <p className="status-message"> Loading...</p>
    if (error) return <p className="status-message error"> Error: {error}</p>

    return (
        <div className="page-container">
            <div className="page-header">
                <h1 className="page-title">Restaurant List</h1>
                <button
                    className="btn btn-primary"
                    onClick={() => navigate('/restaurants/create')}
                >
                    + Create New
                </button>
            </div>

            <div className="table-card">
                <table className="data-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Restaurant Name</th>
                        <th>Owner Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {restaurants.map(r => (
                        <tr key={r.restaurantId}>
                            <td>{r.restaurantId}</td>
                            <td>{r.name}</td>
                            <td>{r.owner}</td>
                            <td>{r.phone}</td>
                            <td>{r.address}</td>
                            <td>
                                <span className={`badge ${r.status === 'ACTIVE' ? 'active' : 'inactive'}`}>
                                    {r.status}
                                </span>
                            </td>
                            <td>
                                <button
                                    className="btn btn-secondary"
                                    onClick={() => navigate(`/restaurants/${r.restaurantId}`)}
                                >
                                    View Detail
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default RestaurantListPage;
