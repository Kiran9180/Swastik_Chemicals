import axios from "axios";

const API_URL = "https://swastik-chemicals.onrender.com/email";

export default {

    sendEmail(billId, formData) {

        return axios.post(
            `${API_URL}/send/${billId}`,
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        );

    }

}
