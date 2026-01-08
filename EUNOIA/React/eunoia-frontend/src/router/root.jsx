import { lazy, Suspense } from 'react';
import { createBrowserRouter } from 'react-router-dom';
import Layout from '../layout/Layout';
import { API_SERVER_HOST } from '../api/defultApi';
import { getMe } from '../api/authApi';
import { useApiError } from '../hooks/useApiError';
import { requireAuth } from '../utils/requireAuth';

const IntroPage = lazy(() => import('../pages/intro/IntroPage'));
const MainPage = lazy(() => import('../pages/main/MainPage'));
const WritePage = lazy(() => import('../pages/write/writePage'));
const AboutPage = lazy(() => import('../pages/about/AboutPage'));
const LoadingPage = lazy(() => import('../pages/loading/LoadingPage'));
const LoginPage = lazy(() => import('../pages/login/LoginPage'));
const SignupPage = lazy(() => import('../pages/signUp/SignupPage'));
const AdminPendingPage = lazy(() => import('../pages/admin/AdminPendingPage'));

const root = createBrowserRouter([
    {
        path: '/',
        children: [
            {
                path: '',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <IntroPage />
                    </Suspense>
                )
            },
            {
                path: 'login',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <LoginPage />
                    </Suspense>
                )
            },
            {
                path: 'signup',
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <SignupPage />
                    </Suspense>
                )
            },
        ]
    },
    {
        path: '/',
        element: <Layout />,
        children: [
            {
                path: 'dashboard',
                loader: requireAuth,
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <MainPage />
                    </Suspense>
                )
            },
            {
                path: 'write',
                loader: requireAuth,
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <WritePage />
                    </Suspense>
                )
            },
            {
                path: 'about',
                loader: requireAuth,
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <AboutPage />
                    </Suspense>
                )
            },
            {
                path: 'pendinglist',
                loader: requireAuth,
                element: (
                    <Suspense fallback={<div>Loading...</div>}>
                        <AdminPendingPage />
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