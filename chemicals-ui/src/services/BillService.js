import axios from "axios";

const API_URL =
    "https://swastik-chemicals.onrender.com/bill";

export default {

    save(bill) {

        return axios.post(
            API_URL + "/save",
            bill
        );

    },

    getAll() {

        return axios.get(
            API_URL + "/all"
        );

    },

    getByOrderId(orderId) {

        return axios.get(
            API_URL +
            "/order/" +
            orderId
        );

    },

    delete(billId) {

        return axios.delete(
            API_URL +
            "/" +
            billId
        );

    }

}