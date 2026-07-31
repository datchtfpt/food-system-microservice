import { useState, useEffect } from "react";
import api from "../api/axiosConfig.js";
import '../pages.css';

function FoodListPage() {

    const [foods, setFoods] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null)

    // ham useEffect goi va truyen ben trong 2 tham so do la ham void va dependence array
    // do ham fetchFood la ham xu ly async tra ve promise
    // nen la phai config truoc roi sau do moi goi
    useEffect(() => {

        // async la modifier, thi ham fetchFood la 1 arrow function, co cau truc: modifier  (parameter) => {body}
        //
        const fetchFoods = async () => {
            try {
                const response = await api.get('/food-service/api/foods');
                setFoods(response.data.data.content);
            } catch (err) {
                setError(err.message)
            } finally {
                setLoading(false)
            }
        }
        fetchFoods()
    }, []);

    if (loading) return <p className="status-message"> Loading...</p>
    if (error) return <p className="status-message error"> Error: {error}</p>

    return (
        <div className="page-container">
            <h1 className="page-title">Food List</h1>
            <table className="data-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Ingredient</th>
                    <th>Restaurant ID</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {/*
                map la method co san cua array
                truyen vao map 1 arrow function, function nay khi dung => () tuc la return ve content
                con => {} (block code) , JS hieu day la return undefined neu ko co return
                */}
                {foods.map(f => (
                    <tr key={f.foodId}>
                        <td>{f.foodId}</td>
                        <td>{f.name}</td>
                        <td>{f.price?.toLocaleString('vi-VN')} ₫</td>
                        <td>{f.ingredient}</td>
                        <td>{f.restaurantId}</td>
                        <td>
                            <span className={`badge ${f.status === 'ACTIVE' ? 'active' : 'inactive'}`}>
                                {f.status}
                            </span>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default FoodListPage