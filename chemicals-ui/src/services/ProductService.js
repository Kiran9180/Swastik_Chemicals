import axios from "axios";

const API_URL =
"http://localhost:8081/product";

export default {

    getAll() {
        return axios.get(
            API_URL + "/all"
        );
    },

    save(product) {
        return axios.post(
            API_URL + "/save",
            product
        );
    },

    update(product) {
        return axios.put(
            API_URL + "/update",
            product
        );
    },

    delete(productId) {
        return axios.delete(
            API_URL + "/" + productId
        );
    },
    getById(productId){

    return axios.get(
        API_URL + "/" + productId
    );

}
}