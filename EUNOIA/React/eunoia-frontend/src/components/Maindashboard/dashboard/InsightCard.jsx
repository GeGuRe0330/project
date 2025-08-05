const InsightCard = ({ insight }) => {
    return (
        <div className="bg-surface shadow-md rounded-xl p-4">
            <h2 className="text-lg font-bold text-textPrimary">EUNOIA가 본 당신</h2>
            <p className="mt-2 text-textSecondary">{insight}</p>
        </div>
    );
};

export default InsightCard;
