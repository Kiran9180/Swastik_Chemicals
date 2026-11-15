import axios from "axios";

const API_URL =
"http://localhost:8081/orderItem";

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