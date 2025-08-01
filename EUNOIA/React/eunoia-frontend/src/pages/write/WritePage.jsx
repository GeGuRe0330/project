import SidebarNav from "../../components/SidebarNav";


const WritePage = () => {
    return (
        <div className="min-h-screen font-sans px-4 py-8">
            {/* 카드: 오늘의 감정 요약 */}
            <div className="bg-surface shadow-sm rounded-xl p-4 mb-4">
                <h2 className="font-bold text-lg text-textPrimary">오늘의 감정 요약</h2>
                <p className="mt-2 text-textSecondary">😊 오늘은 평온한 하루였어요.</p>
            </div>
            {/* 버튼 */}
            <div className="text-center mt-8">
                <button className="bg-primary text-white font-semibold py-2 px-6 rounded-full hover:bg-primary-dark transition">
                    오늘 나를 기록해볼까요?
                </button>
            </div>
        </div>

    );
};

export default WritePage;