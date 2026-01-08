import { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

function formatDate(isoString) {
    const date = new Date(isoString);
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월
    const day = date.getDate().toString().padStart(2, '0');          // 일
    return `${month}/${day}`;
}
function useIsMobile(breakpoint = 640) {
    const [isMobile, setIsMobile] = useState(
        window.matchMedia(`(max-width: ${breakpoint}px)`).matches
    );

    useEffect(() => {
        const media = window.matchMedia(`(max-width: ${breakpoint}px)`);
        const handler = () => setIsMobile(media.matches);
        media.addEventListener("change", handler);
        return () => media.removeEventListener("change", handler);
    }, [breakpoint]);

    return isMobile;
}

const EmotionScoreChart = ({ data }) => {
    const isMobile = useIsMobile();

    if (!data || !Array.isArray(data)) {
        return <div>감정 점수 데이터를 불러오는 중입니다...</div>;
    }

    const formattedData = data.map(item => ({
        ...item,
        createdAt: formatDate(item.createdAt),
        score: item.emotionScore
    }));


    return (
        <div className="bg-surface p-3 md:p-4 rounded-xl shadow-sm h-full flex flex-col">
            <h2 className="text-textPrimary font-bold text-lg mb-4 shrink-0">
                감정 점수 흐름
            </h2>

            {/* 차트 영역: 남은 공간을 전부 차지 */}
            <div className="flex-1 min-h-[180px]">
                <ResponsiveContainer width="100%" height="100%">
                    <LineChart data={formattedData}
                        margin={{ top: 0, right: 20, left: 10, bottom: 0 }}>
                        <CartesianGrid strokeDasharray="10 5" />
                        <XAxis dataKey="createdAt"
                            interval={isMobile ? 1 : 0}
                            height={30}
                        />
                        <YAxis
                            domain={[0, 100]}
                            ticks={[0, 25, 50, 75, 100]}
                            tickFormatter={(value) => {
                                const labels = { 25: "😰", 75: "😌" };
                                return labels[value] || "";
                            }}
                            width={22}
                        />
                        <Tooltip />
                        <Line
                            type="monotone"
                            dataKey="score"
                            stroke="#4DABF7"
                            strokeWidth={2}
                            dot={{ r: isMobile ? 3 : 4 }}
                        />
                    </LineChart>
                </ResponsiveContainer>
            </div>
        </div>
    );

};

export default EmotionScoreChart;
