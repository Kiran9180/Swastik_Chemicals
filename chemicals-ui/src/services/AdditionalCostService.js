import axios from "axios";

const API_URL =
    "http://localhost:8081/additionalCost";

export default {

    save(additionalCost) {

        return axios.post(
            API_URL + "/save",
            additionalCost
        );

    },

    update(additionalCost) {

        return axios.put(
            API_URL + "/update",
            additionalCost
        );

    },

    delete(additionalCostId) {

        return axios.delete(
            API_URL + "/" +
            additionalCostId
        );

    },

    getById(additionalCostId) {

        return axios.get(
            API_URL + "/" +
            additionalCostId
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
    deleteCost(id){

    return axios.delete(
        API_URL + "/" + id
    );

}

}