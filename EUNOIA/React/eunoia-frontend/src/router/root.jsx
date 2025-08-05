import { lazy, Suspense } from 'react';
import { createBrowserRouter } from 'react-router-dom';
import Layout from '../layout/Layout';


const IntroPage = lazy(() => import('../pages/intro/IntroPage'));
const MainPage = lazy(() => import('../pages/main/MainPage'));
const WritePage = lazy(() => import('../pages/write/writePage'));
const InsightPage = lazy(() => import('../pages/insight/InsightPage'));
const LoadingPage = lazy(() => import('../pages/loading/LoadingPage'));

const root = createBrowserRouter([
    {
        path: '/',
        element: (
            <Suspense fallback={<div>Loading...</div>}>
                <IntroPage />
            </Suspense>
        )
    },
    {
        path: '/',
        element: <Layout />,
        children: [
            {
                path: 'dashboard',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <MainPage />
                    </Suspense>
                )
            },
            {
                path: 'write',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <WritePage />
                    </Suspense>
                )
            },
            {
                path: 'insight',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <InsightPage />
                    </Suspense>
                )
            },
            {
                path: 'loading',
                element: (
                    <LoadingPage />
                )
            },
        ],
    },
]);

export default root;