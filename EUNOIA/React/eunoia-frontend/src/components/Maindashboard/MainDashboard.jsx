import EmotionSummaryCard from "./dashboard/EmotionSummaryCard";
import EmotionFlowCard from "./dashboard/EmotionFlowCard";
import WarmMessageCard from "./dashboard/WarmMessageCard";
import InsightCard from "./dashboard/InsightCard";
import EmotionScoreChart from "./dashboard/EmotionScoreChart";
import CardMotion from "../motion/CardMotion";

const MainDashboard = ({ data, scoreDate }) => {
    return (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 p-4 items-stretch">
            {/* 좌측 상단 */}
            <CardMotion index={0} className="h-full">
                <div className="h-[260px] sm:h-[320px] md:h-full">
                    <EmotionScoreChart data={scoreDate} />
                </div>
            </CardMotion>

            {/* 우측 상단 (요약 + 따뜻한 말) - 높이 통일 */}
            <CardMotion index={1} className="flex flex-col gap-4 h-full">
                <div className="flex-1">
                    <EmotionSummaryCard summary={data.emotionSummary} />
                </div>
                <div className="flex-1">
                    <WarmMessageCard messages={data.warmMessages} />
                </div>
            </CardMotion>

            {/* 좌측 하단 */}
            <CardMotion index={2}>
                <EmotionFlowCard flowHint={data.flowHint} />
            </CardMotion>

            {/* 우측 하단 */}
            <CardMotion index={3}>
                <InsightCard insight={data.insightSummary} />
            </CardMotion>
        </div>
    );
};

export default MainDashboard;
