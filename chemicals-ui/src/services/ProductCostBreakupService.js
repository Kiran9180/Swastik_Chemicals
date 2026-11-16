import axios from "axios";

const API_URL =
"https://swastik-chemicals.onrender.com/productCostBreakup";

export default {

    getByProductId(productId){

        return axios.get(
            API_URL +
            "/product/" +
            productId
        );
    },

    save(productCostBreakup){

        return axios.post(
            API_URL +
            "/save",
            productCostBreakup
        );
    },

    update(productCostBreakup){

        return axios.put(
            API_URL +
            "/update",
            productCostBreakup
        );
    },

    delete(id){

        return axios.delete(
            API_URL +
            "/" +
            id
        );
    }

}