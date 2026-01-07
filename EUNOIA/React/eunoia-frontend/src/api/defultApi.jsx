import axios from "axios";
import { normalizeApiError } from "../utils/normalizeApiError";

export const API_SERVER_HOST = 'http://localhost:8080';

// 세션쿠키 ( JSESSIONID ) 자동 포함
export const api = axios.create({
    baseURL: API_SERVER_HOST,
    withCredentials: true,
});

//  응답 인터셉터
api.interceptors.response.use(
    (response) => response,
    (error) => {
        const normalized = normalizeApiError(error);
        return Promise.reject(normalized);
    }
);