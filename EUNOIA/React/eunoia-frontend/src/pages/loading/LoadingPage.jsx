import { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { getEmotionEntry, postWarmMessages, updateAnalysis } from '../../api/EunoiaApi';
import { motion, AnimatePresence } from 'framer-motion';

const LoadingPage = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const entryId = location.state?.entryId;

    const [warmMessages, setWarmMessages] = useState([]);
    const [visibleIndex, setVisibleIndex] = useState(0);

    // 분석 실행
    useEffect(() => {
        const runAnalysis = async () => {
            try {
                if (!entryId) throw new Error('entryId 없음');

                const entry = await getEmotionEntry(entryId);
                const warm = await postWarmMessages(entryId, { content: entry.content });

                // 콘솔 확인
                console.log('[✅ GPT 응답]', warm);

                await updateAnalysis(warm.id);

                if (!Array.isArray(warm.warmMessages)) {
                    throw new Error('warmMessages 형식 오류');
                }

                setWarmMessages(warm.warmMessages);
                setVisibleIndex(0);

            } catch (err) {
                console.error('GPT 분석 오류:', err);
                alert('감정 분석 중 문제가 발생했어요 😢');
                navigate('/dashboard');
            }
        };

        runAnalysis();

        const timer = setTimeout(() => navigate('/dashboard'), 30000); // fallback 이동
        return () => clearTimeout(timer);
    }, [entryId, navigate]);

    // 메시지 순차 전환
    useEffect(() => {
        if (warmMessages.length === 0) return;

        const interval = setInterval(() => {
            setVisibleIndex((prev) => (prev + 1) % warmMessages.length);
        }, 5500);

        return () => clearInterval(interval);
    }, [warmMessages]);

    return (
        <div className="flex flex-col items-center justify-center h-screen bg-[#fdfaf6] text-gray-800">
            {/* 분석 문구 */}
            <div className="text-xl md:text-2xl font-semibold mb-4">
                EUNOIA가 당신의 글을 읽고 있어요...
            </div>
            <div className="text-sm mb-10 text-gray-500">
                여기까지 잘 걸어왔어요. 잠시만 기다려주세요 🤍
            </div>

            {/* 메시지 출력 */}
            <div className="h-12 min-h-[48px] flex items-center justify-center">
                {warmMessages.length > 0 ? (
                    <AnimatePresence mode="wait">
                        <motion.p
                            key={visibleIndex}
                            initial={{ opacity: 0 }}
                            animate={{ opacity: 1 }}
                            exit={{ opacity: 0 }}
                            transition={{ duration: 1.2 }}
                            className="text-gray-700 text-2xl font-semibold text-center px-4"
                        >
                            “{warmMessages[visibleIndex]}”
                        </motion.p>
                    </AnimatePresence>
                ) : (
                    <motion.p
                        key="loading"
                        initial={{ opacity: 0 }}
                        animate={{ opacity: 1 }}
                        exit={{ opacity: 0 }}
                        transition={{ duration: 1.2 }}
                        className="text-gray-500 text-lg font-medium text-center"
                    >
                        EUNOIA가 당신에게 전할 말을 고르고 있어요.
                    </motion.p>
                )}
            </div>

            {/* 스피너 */}
            <div className="mt-10 w-10 h-10 border-4 border-primary border-t-transparent rounded-full animate-spin" />
        </div>
    );
};

export default LoadingPage;
