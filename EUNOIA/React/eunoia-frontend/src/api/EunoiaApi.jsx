import { api, API_SERVER_HOST } from './defultApi';

// 감정 분석 API prefix
const ANALYSIS_PREFIX = `${API_SERVER_HOST}/api/analyses`;

// 최신 감정 분석 1건 조회
export const getLatestAnalysis = async () => {
    const res = await api.get(`${ANALYSIS_PREFIX}/me/latest`);
    return res.data.data;
};

// 감정 점수 흐름 조회
export const getEmotionScores = async () => {
    const res = await api.get(`${ANALYSIS_PREFIX}/me/scores`);
    return res.data.data;
};

// 감정글 저장 (예: /api/emotion-entry)
export const postEmotionEntry = async (entryObj) => {
    const res = await api.post(`/api/emotion-entries`, entryObj);
    return res.data.data;
};

// 감정글 조회
export const getEmotionEntry = async (entryId) => {
    const res = await api.get(`/api/emotion-entries/${entryId}`);
    return res.data.data;
};

// 따뜻한 말 분석 요청
export const postWarmMessages = async (entryId, dto = {}) => {
    const res = await api.post(`${ANALYSIS_PREFIX}/warm-messages/${entryId}`, dto);
    return res.data.data;
};

// 전체 분석 업데이트
export const updateAnalysis = async (analysisId) => {
    const res = await api.put(`${ANALYSIS_PREFIX}/${analysisId}`, {});
    return res.data.data;
};
