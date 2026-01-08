const EmotionFlowCard = ({ flowHint }) => {
    return (
        <div className="bg-surface shadow-md rounded-xl p-4">
            <h2 className="text-lg font-bold text-textPrimary">
                이번 분석에서 당신의 감정은
            </h2>
            <p className="mt-2 text-textSecondary">{flowHint}</p>
        </div>
    );
};

export default EmotionFlowCard;
