import axios from 'axios';

export const API_SERVER_HOST = 'http://localhost:8080';

// 감정 분석 API prefix
const ANALYSIS_PREFIX = `${API_SERVER_HOST}/api/analyses`;

// 최신 감정 분석 1건 조회
export const getLatestAnalysis = async (memberId) => {
    const res = await axios.get(`${ANALYSIS_PREFIX}/member/${memberId}/latest`);
    return res.data;
};

// 감정 점수 흐름 조회
export const getEmotionScores = async (memberId) => {
    const res = await axios.get(`${ANALYSIS_PREFIX}/scores/member/${memberId}`);
    return res.data;
};

// 감정글 저장 (예: /api/emotion-entry)
export const postEmotionEntry = async (memberId, entryObj) => {
    const res = await axios.post(`${API_SERVER_HOST}/api/emotion-entries/${memberId}`, entryObj);
    return res.data;
};

// 감정글 조회
export const getEmotionEntry = async (entryId) => {
    const res = await axios.get(`${API_SERVER_HOST}/api/emotion-entries/${entryId}`);
    return res.data;
};

// 따뜻한 말 분석 요청
export const postAnalysis = async (entryId, dto) => {
    const res = await axios.post(`${API_SERVER_HOST}/api/analyses/warm-messages/${entryId}`, dto);
    return res.data;
};

// 전체 분석 업데이트
export const updateAnalysis = async (entryId) => {
    const res = await axios.put(`${API_SERVER_HOST}/api/analyses/${entryId}`, {});
    return res.data;
};
