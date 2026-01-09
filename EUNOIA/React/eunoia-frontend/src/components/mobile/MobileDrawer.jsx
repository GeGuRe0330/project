import { useEffect } from "react";
import { Link, useLocation } from "react-router-dom";

const navItems = [
    { to: "/dashboard", label: "감정 대시보드" },
    { to: "/write", label: "감정 기록하기" },
    { to: "/about", label: "유노이아란?" },
];

const MobileDrawer = ({ open, onClose, me, onLogout }) => {
    const location = useLocation();

    const isActive = (path) =>
        location.pathname === path || location.pathname.startsWith(path);

    const adminItems =
        me?.role === "ADMIN" ? [{ to: "/pendinglist", label: "관리자 승인" }] : [];

    const linkClass = (active) =>
        [
            "px-4 py-3 rounded-xl transition-all text-base",
            "hover:bg-white/40",
            active
                ? "bg-white/60 font-semibold border-l-4 border-primary-dark"
                : "text-gray-800",
        ].join(" ");

    // open일 때 스크롤 방지(모바일 UX)
    useEffect(() => {
        if (!open) return;
        const prev = document.body.style.overflow;
        document.body.style.overflow = "hidden";
        return () => {
            document.body.style.overflow = prev;
        };
    }, [open]);

    if (!open) return null;

    return (
        <div className="md:hidden fixed inset-0 z-50">
            {/* Dim */}
            <div
                className="absolute inset-0 bg-black/30"
                onClick={onClose}
                aria-hidden="true"
            />

            {/* Panel */}
            <aside className="absolute top-0 right-0 h-full w-72 bg-primary-light shadow-2xl p-6 font-handwriting">
                {/* 상단 */}
                <div className="flex items-center justify-between">
                    <div className="text-xl font-bold text-primary-dark font-serif">EUNOIA</div>
                    <button
                        type="button"
                        onClick={onClose}
                        className="rounded-xl bg-white/50 hover:bg-white/70 transition px-3 py-2 text-sm font-semibold"
                        aria-label="메뉴 닫기"
                    >
                        ✕
                    </button>
                </div>

                {/* 유저 박스 */}
                <div className="mt-6 rounded-2xl bg-white/45 p-4 text-center">
                    <div className="font-sans font-semibold text-textPrimary">
                        {me?.nickname ? `${me.nickname} 님` : "반가워요!"}
                    </div>
                    <div className="font-serif mt-1 text-sm text-textSecondary truncate">{me?.email}</div>

                    {me?.role === "ADMIN" && (
                        <div className="font-serif mt-2 inline-block rounded-md bg-primary-dark/90 px-2 py-0.5 text-xs font-semibold text-white">
                            ADMIN
                        </div>
                    )}
                </div>

                {/* 메뉴 */}
                <nav className="mt-6 flex flex-col gap-2">
                    {navItems.map((item) => (
                        <Link
                            key={item.to}
                            to={item.to}
                            onClick={onClose}
                            className={linkClass(isActive(item.to))}
                        >
                            {item.label}
                        </Link>
                    ))}
                </nav>

                {/* 하단: 관리자 + 로그아웃 */}
                <div className="mt-6 pt-4 border-t border-white/40">
                    {adminItems.length > 0 && (
                        <div className="flex flex-col gap-2 mb-4">
                            {adminItems.map((item) => (
                                <Link
                                    key={item.to}
                                    to={item.to}
                                    onClick={onClose}
                                    className={linkClass(isActive(item.to))}
                                >
                                    {item.label}
                                </Link>
                            ))}
                        </div>
                    )}

                    <button
                        type="button"
                        onClick={() => {
                            onClose();
                            onLogout();
                        }}
                        className="w-full rounded-xl bg-white/50 hover:bg-white/70 transition px-4 py-3 text-sm font-semibold"
                    >
                        로그아웃
                    </button>
                </div>
            </aside>
        </div>
    );
};

export default MobileDrawer;
