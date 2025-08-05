const WarmMessageCard = ({ messages }) => {
    return (
        <div className="bg-surface shadow-md rounded-xl p-4">
            <h2 className="text-lg font-bold text-textPrimary">
                EUNOIA가 당신에게 해주고 싶은 말
            </h2>
            <ul className="mt-2 list-disc list-inside space-y-1 text-textSecondary">
                {messages.map((msg, index) => (
                    <li key={index}>{msg}</li>
                ))}
            </ul>
        </div>
    );
};

export default WarmMessageCard;
