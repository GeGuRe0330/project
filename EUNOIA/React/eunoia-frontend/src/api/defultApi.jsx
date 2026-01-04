import axios from "axios";

export const API_SERVER_HOST = 'http://localhost:8080';

// 세션쿠키 ( JSESSIONID ) 자동 포함
export const api = axios.create({
    baseURL: API_SERVER_HOST,
    withCredentials: true,
});