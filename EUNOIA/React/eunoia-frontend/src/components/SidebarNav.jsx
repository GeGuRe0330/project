// src/components/SidebarNav.jsx
import { Link, useLocation } from 'react-router-dom';

const SidebarNav = () => {
    const location = useLocation();

    const isActive = (path) => location.pathname === path;

    return (
        <aside className="fixed top-0 left-0 h-full w-52 bg-primary-light text-gray-800 shadow-md p-6 font-handwriting z-50">
            <div className="mb-10 text-2xl font-bold">
                <span className="text-primary-dark">EUNOIA</span>
            </div>

            <nav className="flex flex-col gap-4">
                <Link
                    to="/dashboard"
                    className={`hover:text-primary-dark transition-all ${isActive('/') ? 'font-bold underline' : ''
                        }`}
                >
                    감정 대시보드
                </Link>
                <Link
                    to="/write"
                    className={`hover:text-primary-dark transition-all ${isActive('/write') ? 'font-bold underline' : ''
                        }`}
                >
                    감정 기록하기
                </Link>
                <Link
                    to="/insight"
                    className={`hover:text-primary-dark transition-all ${isActive('/insight') ? 'font-bold underline' : ''
                        }`}
                >
                    GPT 통찰
                </Link>
            </nav>
        </aside>
    );
};

export default SidebarNav;
