import { RouterProvider } from "react-router-dom";
import SidebarNav from "../../components/SidebarNav";


const InsightPage = () => {
    return (
        <div className="min-h-screen font-sans px-4 py-8">
            {/* 카드: GPT 통찰 요약 */}
            <div className="bg-surface shadow-sm rounded-xl p-4 mb-4">
                <h2 className="font-bold text-lg text-textPrimary">EUNOIA가 봤을때...</h2>
                <p className="mt-2 text-textSecondary">최근 감정 흐름은 ‘지침 → 회복’으로 향하고 있어요.</p>
            </div>
        </div>
    );
};

export default InsightPage;