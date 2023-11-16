import axios from 'axios';


const token=localStorage.getItem('token');
const api = axios.create({
 

    baseURL: "http://localhost:3456/crudApp",
    headers: {
      'Content-Type':'application/json',
      'Access-Control-Allow-Origin':'*',
      'Authorization':`Bearer ${token}`
    }
  });
  
  export default api;