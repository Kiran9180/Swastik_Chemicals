import axios from "axios";

const API_URL =
    "https://swastik-chemicals.onrender.com/orderCostBreakup";

export default {

    save(orderCostBreakup) {

        return axios.post(
            API_URL + "/save",
            orderCostBreakup
        );

    },

    update(orderCostBreakup) {

        return axios.put(
            API_URL + "/update",
            orderCostBreakup
        );

    },

    delete(orderCostBreakupId) {

        return axios.delete(
            API_URL + "/" +
            orderCostBreakupId
        );

    },

    getById(orderCostBreakupId) {

        return axios.get(
            API_URL + "/" +
            orderCostBreakupId
        );

    },

    getAll() {

        return axios.get(
            API_URL + "/all"
        );

    },

    getByOrderItemId(orderItemId) {

        return axios.get(
            API_URL +
            "/orderItem/" +
            orderItemId
        );

    },

    copyProductCostBreakup(
        productId,
        orderItemId
    ) {

        return axios.post(
            API_URL +
            "/copy" +
            "?productId=" + productId +
            "&orderItemId=" + orderItemId
        );

    },

    getOrderItemTotalCost(
        orderItemId
    ) {

        return axios.get(
            API_URL +
            "/itemTotal/" +
            orderItemId
        );

    },

    getOrderTotalCost(
        orderId
    ) {

        return axios.get(
            API_URL +
            "/orderTotal/" +
            orderId
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