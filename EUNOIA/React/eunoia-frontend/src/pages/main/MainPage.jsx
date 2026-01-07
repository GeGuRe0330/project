import { useEffect, useState } from "react";
import MainDashboard from "../../components/Maindashboard/MainDashboard";
import { getLatestAnalysis, getEmotionScores } from "../../api/EunoiaApi";

const MainPage = () => {
    const [data, setData] = useState(null);
    const [scoreData, setScoreData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // const memberId = 1; // 추후 인증 연동 시 동적 처리

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [latest, scores] = await Promise.all([
                    getLatestAnalysis(),
                    getEmotionScores()
                ]);
                setData(latest);
                setScoreData(scores);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    if (loading) return <div className="text-center">불러오는 중...</div>;
    if (error) return <div className="text-red-500 text-center">{error}</div>;
    if (!data) return <div className="text-center">아직 분석된 감정 글이 없습니다.</div>;

    return (
        <div className="min-h-screen font-sans px-4 py-8">
            <MainDashboard data={data} scoreDate={scoreData} />
        </div>
    );
};

export default MainPage;
