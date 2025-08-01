import { Outlet } from "react-router";
import SidebarNav from "../components/SidebarNav";

const Layout = () => {
    return (
        <div className="flex">
            <SidebarNav />
            <main className="ml-52 p-6 w-full min-h-screen">
                <Outlet />
            </main>
        </div>
    );
};

export default Layout;