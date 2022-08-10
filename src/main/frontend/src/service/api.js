import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
  }
});

if(localStorage.getItem('user')) {
  const user = localStorage.getItem('user');
  api.defaults.headers.common["Authorization"] = "Basic " + user
}

export default api;