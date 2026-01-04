import { api, API_SERVER_HOST } from './defultApi';

// 로그인확인용 요청
export const getMe = async () => {
    const res = await api.get(`${API_SERVER_HOST}/api/members/user/me`);
    return res.data;
};

// 로그인
export const login = async ({ username, password }) => {
    const body = new URLSearchParams();
    body.append('username', username);
    body.append('password', password);

    const res = await api.post(`${API_SERVER_HOST}/login`, body, {
        headers: { "Content-Type": 'application/x-www-form-urlencoded' },
        withCredentials: true,
    });
    return res.data;
}

// 회원가입 요청
export const signUp = async (payload) => {
    const res = await api.post(`${API_SERVER_HOST}/api/members/signup`, payload)
    return res.data
};