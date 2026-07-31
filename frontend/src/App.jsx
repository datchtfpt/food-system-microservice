import {Route, Routes} from "react-router-dom";
import Navbar from "./components/Navbar.jsx";
import RestaurantListPage from "./pages/RestaurantListPage.jsx";
import FoodListPage from "./pages/FoodListPage.jsx";
import RestaurantDetailPage from "./pages/RestaurantDetailPage.jsx";
import CreateRestaurantPage from "./pages/CreateRestaurantPage.jsx";


function App() {
  return (
      <>
        <Navbar/>
        <Routes>
          <Route path="/" element={<RestaurantListPage/>} />
            <Route path="/foods" element={<FoodListPage/>} />
            <Route path="/restaurants/create" element={<CreateRestaurantPage/>}/>
            <Route path="/restaurants/:id" element={<RestaurantDetailPage/>}/>
        </Routes>
      </>
  )
}

export default App