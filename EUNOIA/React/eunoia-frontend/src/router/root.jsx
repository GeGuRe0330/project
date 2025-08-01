import { lazy, Suspense } from 'react';
import { createBrowserRouter } from 'react-router-dom';
import Layout from '../layout/Layout';

const MainPage = lazy(() => import('../pages/main/MainPage'));
const WritePage = lazy(() => import('../pages/write/writePage'));
const InsightPage = lazy(() => import('../pages/insight/InsightPage'));

const root = createBrowserRouter([
    {
        path: '/',
        element: <Layout />,
        children: [
            {
                index: true,
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <MainPage />,
                    </Suspense>
                )
            },
            {
                path: 'write',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <WritePage />,
                    </Suspense>
                )
            },
            {
                path: 'insight',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <InsightPage />,
                    </Suspense>
                )
            },
        ],
    },
]);

export default root;