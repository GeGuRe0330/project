const EmotionSummaryCard = ({ summary }) => {
    return (
        <div className="bg-surface shadow-md rounded-xl p-4">
            <h2 className="text-lg font-bold text-textPrimary">오늘의 감정 요약</h2>
            <p className="mt-2 text-textSecondary">{summary}</p>
        </div>
    );
};

export default EmotionSummaryCard;
