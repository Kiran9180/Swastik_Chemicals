import axios from "axios";

const API_URL =
    "http://localhost:8081/customerOrder";

const USER_URL =
    "http://localhost:8081/users";

export default {

    checkCustomer(mobile, email) {

        return axios.get(
            USER_URL + "/check",
            {
                params: {
                    mobile,
                    email
                }
            }
        );
    },

    getAll() {
        return axios.get(API_URL + "/all");
    },

    getPending() {
        return axios.get(API_URL + "/pending");
    },

    getApproved() {
        return axios.get(API_URL + "/approved");
    },

    save(order) {
        return axios.post(
            API_URL + "/save",
            order
        );
    },

    update(order) {
        return axios.put(
            API_URL + "/update",
            order
        );
    },

    approve(orderId) {
        return axios.put(
            API_URL +
            "/approve/" +
            orderId
        );
    },

    reject(orderId) {
        return axios.put(
            API_URL +
            "/reject/" +
            orderId
        );
    },

    delete(orderId) {
        return axios.delete(
            API_URL +
            "/" +
            orderId
        );
    }
}