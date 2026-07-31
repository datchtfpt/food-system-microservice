import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/axiosConfig.js";
import '../pages.css';

function RestaurantDetailPage() {

    const { id } = useParams()
    const navigate = useNavigate()

    const [restaurant, setRestaurant] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null)

    useEffect(() => {
        const fetchRestaurant = async () => {
            try {
                //  phải có await ở đây
                const response = await api.get(`/restaurant-service/api/restaurants/${id}`);
                setRestaurant(response.data.data)
            } catch (err) {
                setError(err.message)
            } finally {
                setLoading(false)
            }
        }
        fetchRestaurant()
    }, [id]);

    if (loading) return <p className="status-message"> Loading...</p>
    if (error) return <p className="status-message error"> Error: {error}</p>
    if (!restaurant) return <p className="status-message">No restaurant was found.</p>

    return (
        <div className="page-container">
            <div className="back-row">
                <button className="btn btn-outline" onClick={() => navigate(-1)}>← Return</button>
            </div>

            <h1 className="page-title">Restaurant Detail</h1>

            <div className="detail-card">
                <div className="detail-card-header">
                    <h2>{restaurant.name}</h2>
                    <p>ID: #{restaurant.restaurantId}</p>
                </div>

                <table className="detail-table">
                    <tbody>
                    <tr>
                        <td>Owner</td>
                        {/*  dùng {restaurant.owner} thay vì ${restaurant.owner} */}
                        <td>{restaurant.owner}</td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td>{restaurant.phone}</td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td>{restaurant.address}</td>
                    </tr>
                    <tr>
                        <td>Price From</td>
                        <td>{restaurant.priceFrom?.toLocaleString('vi-VN')} ₫</td>
                    </tr>
                    <tr>
                        <td>Price To</td>
                        <td>{restaurant.priceTo?.toLocaleString('vi-VN')} ₫</td>
                    </tr>
                    <tr>
                        <td>Open Date</td>
                        <td>{restaurant.openDate}</td>
                    </tr>
                    <tr>
                        <td>Category ID</td>
                        <td>{restaurant.categoryId}</td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <span className={`badge ${restaurant.status === 'ACTIVE' ? 'active' : 'inactive'}`}>
                                {restaurant.status}
                            </span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default RestaurantDetailPage