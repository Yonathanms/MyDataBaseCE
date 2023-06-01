import axios from "axios";

export default class PersonasServices{

    url = "http://localhost:5173/api/v1"

    getAll(){
        return axios.get(this.url + "all");
    }

}