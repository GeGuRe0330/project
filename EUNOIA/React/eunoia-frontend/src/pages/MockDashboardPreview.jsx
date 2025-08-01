

export default function MockDashboard() {
    return (
        <div className="min-h-screen font-sans px-4 py-8">
            {/* 카드: 오늘의 감정 요약 */}
            <div className="bg-surface shadow-sm rounded-xl p-4 mb-4">
                <h2 className="font-bold text-lg text-textPrimary">오늘의 감정 요약</h2>
                <p className="mt-2 text-textSecondary">😊 오늘은 평온한 하루였어요.</p>
            </div>

            {/* 카드: 최근 감정 변화 */}
            <div className="bg-surface shadow-sm rounded-xl p-4 mb-4">
                <h2 className="font-bold text-lg text-textPrimary">최근 감정 변화</h2>
                <p className="mt-2 text-textSecondary">[여기에 라인 차트 들어갈 예정]</p>
            </div>

            {/* 카드: 오늘의 따뜻한 한마디 */}
            <div className="bg-surface shadow-sm rounded-xl p-4 mb-4 italic">
                <h2 className="font-bold text-lg text-textPrimary">오늘의 따뜻한 한마디</h2>
                <p className="mt-2 text-textSecondary">“당신이 지나는 하루가 오늘을 더 단단하게 만들어줍니다.”</p>
            </div>

            {/* 카드: GPT 통찰 요약 */}
            <div className="bg-surface shadow-sm rounded-xl p-4 mb-4">
                <h2 className="font-bold text-lg text-textPrimary">EUNOIA가 봤을때...</h2>
                <p className="mt-2 text-textSecondary">최근 감정 흐름은 ‘지침 → 회복’으로 향하고 있어요.</p>
            </div>

            {/* 버튼 */}
            <div className="text-center mt-8">
                <button className="bg-primary text-white font-semibold py-2 px-6 rounded-full hover:bg-primary-dark transition">
                    오늘 나를 기록해볼까요?
                </button>
            </div>
        </div>
    );
}
