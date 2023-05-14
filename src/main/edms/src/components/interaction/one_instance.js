import axios from "axios";


export const axiosGlobal =
    axios.create({
        //balaji - removing baseURL for cors, we have a proxy in package.json
        baseURL: 'http://localhost:8080/',
        //baseURL:'http://wark.fun/',
        timeout: 50000,
    
        headers: {

            'Content-Type': 'application/json',
            // 'Accept': 'application/json'

        }
    });
