import axios from "axios";
// tao ra bien instance tu axios, o day la api, api co cac method()
const api = axios.create({
    baseURL : "http://localhost:8080"
});

export default api
