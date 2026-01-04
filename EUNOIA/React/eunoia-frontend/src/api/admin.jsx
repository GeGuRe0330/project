import { api, API_SERVER_HOST } from './defultApi';

// PENDING 상태인 유저 리스트 조회
export const getPendingMembers = async () => {
    const res = await api.get(`${API_SERVER_HOST}/api/admin/members/pending`);
    return res.data;
};

// PENDING 유저 승인하기
export const approveMember = async (memberId) => {
    const res = await api.patch(`${API_SERVER_HOST}/api/admin/members/${memberId}/approve`);
};