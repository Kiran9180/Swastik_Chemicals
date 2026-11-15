import axios from "axios";

const API_URL =
    "http://localhost:8081/auth";

export default {

    login(user) {

        return axios.post(
            API_URL + "/login",
            user
        );

    }

}