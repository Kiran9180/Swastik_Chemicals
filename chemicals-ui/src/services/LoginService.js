import axios from "axios";

const API_URL =
    "https://swastik-chemicals.onrender.com/auth";

export default {

    login(user) {

        return axios.post(
            API_URL + "/login",
            user
        );

    }

}