import { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { approveMember, getPendingMembers } from '../../api/admin';

const AdminPendingPage = () => {
    const navigate = useNavigate();

    const [pending, setPending] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [actionLoadingId, setActionLoadingId] = useState(null);
    const [errorMsg, setErrorMsg] = useState('');

    const count = useMemo(() => pending.length, [pending]);

    const fetchPending = async () => {
        setErrorMsg('');
        setIsLoading(true);
        try {
            const res = await getPendingMembers();
            setPending(res ?? []);
        } catch (err) {
            const status = err?.response?.status;
            if (status === 401) setErrorMsg('로그인이 필요합니다. 로그인 후 시도해주세요.');
            else if (status === 403) setErrorMsg('관리자 권한이 없습니다. 로그인 후 시도해주세요.');
            else setErrorMsg('대기 목록을 불러오지 못했습니다. 이는 서버문제일 수 있습니다.');
        } finally {
            setIsLoading(false);
        }
    };

    const handleApprove = async (id) => {
        if (!id) return;
        setErrorMsg('');
        setActionLoadingId(id);

        try {
            await approveMember(id);
            setPending((prev) => prev.filter((m) => m.id !== id));
        } catch (err) {
            const message =
                err?.response?.data?.message ||
                (err?.response?.status === 403
                    ? '관리자 권한이 없어요.'
                    : '승인 처리에 실패했어요. 잠시 후 다시 시도해주세요.');
            setErrorMsg(message);
        } finally {
            setActionLoadingId(null);
        }
    };

    useEffect(() => {
        fetchPending();
    }, []);

    return (
        <div className="min-h-screen w-full flex justify-center text-textPrimary font-sans">
            <div className="w-full max-w-4xl px-6 py-10">
                {/* 헤더 */}
                <div className="flex items-end justify-between gap-4 mb-8">
                    <div>
                        <h1 className="text-3xl md:text-4xl font-handwriting">Admin · 승인 대기</h1>
                        <p className="text-sm text-gray-500 mt-2">
                            현재까지 승인 대기중인 회원 목록입니다.
                        </p>
                    </div>

                    <div className="flex items-center gap-2">
                        <button
                            onClick={fetchPending}
                            className="rounded-xl border border-black/10 bg-white/70 px-4 py-2 text-sm hover:bg-white transition"
                            disabled={isLoading}
                        >
                            새로고침
                        </button>
                        <button
                            onClick={() => navigate('/dashboard')}
                            className="rounded-xl border border-black/10 bg-white/70 px-4 py-2 text-sm hover:bg-white transition"
                        >
                            대시보드로
                        </button>
                    </div>
                </div>

                {/* 에러 */}
                {errorMsg && (
                    <div className="mb-6 rounded-xl border border-black/10 bg-white/80 px-4 py-3 text-sm text-red-600">
                        {errorMsg}
                    </div>
                )}

                {/* 상태 */}
                <div className="mb-4 flex items-center justify-between">
                    <div className="text-sm text-gray-600">
                        현재 대기 인원: <span className="font-semibold">{count}</span> 명
                    </div>
                </div>

                {/* 로딩 */}
                {isLoading ? (
                    <div className="rounded-2xl border border-black/10 bg-white/70 p-8">
                        <div className="text-sm text-gray-500">대기 목록 불러오는 중...</div>
                    </div>
                ) : pending.length === 0 ? (
                    <div className="rounded-2xl border border-black/10 bg-white/70 p-10 text-center">
                        <div className="text-lg font-semibold">대기 중인 회원이 없어요 🎉</div>
                        <div className="text-sm text-gray-500 mt-2">
                            새로운 가입 요청이 들어오면 이 곳에서 확인할 수 있어요.
                        </div>
                    </div>
                ) : (
                    <div className="space-y-3">
                        {pending.map((m) => (
                            <div
                                key={m.id}
                                className="rounded-2xl border border-black/10 bg-white/75 p-5 flex flex-col md:flex-row md:items-center md:justify-between gap-4"
                            >
                                <div className="min-w-0">
                                    <div className="flex flex-wrap items-center gap-2">
                                        <span className="text-base font-semibold truncate">{m.nickname ?? '(닉네임 없음)'}</span>
                                        <span className="text-xs rounded-full bg-black/5 px-2 py-1">
                                            #{m.id}
                                        </span>
                                        <span className="text-xs rounded-full bg-black/5 px-2 py-1">
                                            {m.status ?? 'PENDING'}
                                        </span>
                                        <span className="text-xs rounded-full bg-black/5 px-2 py-1">
                                            {m.role ?? 'USER'}
                                        </span>
                                    </div>

                                    <div className="text-sm text-gray-600 mt-2 break-all">
                                        {m.email}
                                    </div>

                                    <div className="text-xs text-gray-500 mt-2 flex flex-wrap gap-3">
                                        <span>나이: {m.age ?? '-'}</span>
                                        <span>성별: {m.gender ?? '-'}</span>
                                        <span>
                                            가입일: {m.createdAt ? new Date(m.createdAt).toLocaleString() : '-'}
                                        </span>
                                    </div>
                                </div>

                                <div className="flex items-center gap-2">
                                    <button
                                        onClick={() => handleApprove(m.id)}
                                        disabled={actionLoadingId === m.id}
                                        className="rounded-xl bg-black text-white px-4 py-2 text-sm hover:opacity-90 transition disabled:opacity-50"
                                    >
                                        {actionLoadingId === m.id ? '승인 중...' : '승인'}
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                )}

                {/* 푸터 도움말 */}
                <div className="mt-10 text-xs text-gray-400">
                    승인을 누르면 해당 계정은 <span className="font-semibold text-black">즉시 로그인 가능</span>.
                </div>
            </div>
        </div>
    );
};

export default AdminPendingPage;
