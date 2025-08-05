import { motion } from 'framer-motion';
import EmotionSummaryCard from "./dashboard/EmotionSummaryCard";
import EmotionFlowCard from "./dashboard/EmotionFlowCard";
import WarmMessageCard from "./dashboard/WarmMessageCard";
import InsightCard from "./dashboard/InsightCard";
import EmotionScoreChart from "./dashboard/EmotionScoreChart";

const cardVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: (i) => ({
        opacity: 1,
        y: 0,
        transition: {
            delay: i * 0.2,
            duration: 0.8,
            ease: 'easeOut',
        }
    }),
};

const MainDashboard = ({ data, scoreDate }) => {
    return (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 p-4 items-stretch">
            {/* 좌측 상단 */}
            <motion.div
                custom={0}
                initial="hidden"
                animate="visible"
                variants={cardVariants}
                className="h-full"
            >
                <div className="h-full">
                    <EmotionScoreChart data={scoreDate} />
                </div>
            </motion.div>

            {/* 우측 상단 (요약 + 따뜻한 말) - 높이 통일 */}
            <motion.div
                custom={1}
                initial="hidden"
                animate="visible"
                variants={cardVariants}
                className="flex flex-col gap-4 h-full"
            >
                <div className="flex-1">
                    <EmotionSummaryCard summary={data.emotionSummary} />
                </div>
                <div className="flex-1">
                    <WarmMessageCard messages={data.warmMessages} />
                </div>
            </motion.div>
            {/* 좌측 하단 */}
            <motion.div
                custom={2}
                initial="hidden"
                animate="visible"
                variants={cardVariants}
            >
                <EmotionFlowCard flowHint={data.flowHint} />
            </motion.div>

            {/* 우측 하단 */}
            <motion.div
                custom={3}
                initial="hidden"
                animate="visible"
                variants={cardVariants}
            >
                <InsightCard insight={data.insightSummary} />
            </motion.div>
        </div>
    );
};

export default MainDashboard;
