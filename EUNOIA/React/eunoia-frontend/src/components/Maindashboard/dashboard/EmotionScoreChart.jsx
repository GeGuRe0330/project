import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

function formatDate(isoString) {
    const date = new Date(isoString);
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월
    const day = date.getDate().toString().padStart(2, '0');          // 일
    return `${month}/${day}`;
}

const EmotionScoreChart = ({ data }) => {
    console.log(data);
    if (!data || !Array.isArray(data)) {
        return <div>감정 점수 데이터를 불러오는 중입니다...</div>;
    }

    const formattedData = data.map(item => ({
        ...item,
        createdAt: formatDate(item.createdAt),
        score: item.emotionScore
    }));

    return (
        <div className="bg-surface p-4 rounded-xl shadow-sm">
            <h2 className="text-textPrimary font-bold text-lg mb-4">감정 점수 흐름</h2>
            <ResponsiveContainer width="100%" height={250}>
                <LineChart data={formattedData}>
                    <CartesianGrid strokeDasharray="10 5" />
                    <XAxis dataKey="createdAt" />
                    <YAxis
                        domain={[0, 100]}
                        ticks={[0, 25, 50, 75, 100]}
                        tickFormatter={(value) => {
                            const labels = {
                                25: '😰',
                                75: '😌',
                            };
                            return labels[value] || '';
                        }}
                    />
                    <Tooltip />
                    <Line type="monotone" dataKey="score" stroke="#4DABF7" strokeWidth={2} dot={{ r: 4 }} />
                </LineChart>
            </ResponsiveContainer>
        </div>
    );
};

export default EmotionScoreChart;
