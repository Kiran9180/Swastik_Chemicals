import axios from "axios";

const API_URL =
"https://swastik-chemicals.onrender.com/orderItem";

export default {

    save(orderItem) {
        return axios.post(
            API_URL + "/save",
            orderItem
        );
    },

    getByOrderId(orderId) {
        return axios.get(
            API_URL +
            "/order/" +
            orderId
        );
    }
}