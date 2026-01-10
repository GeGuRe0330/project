import CardMotion from "../../components/motion/CardMotion";

// 선택) 뱃지 UI를 깔끔하게 쓰려고 작은 컴포넌트로 분리
const Badge = ({ children, tone = "primary" }) => {
    const toneMap = {
        primary: "bg-primary-dark/90",
        secondary: "bg-secondary-light/80",
        ok: "bg-emerald-500/70",
        warn: "bg-amber-500/70",
        danger: "bg-rose-500/70",
        neutral: "bg-white/35",
    };

    return (
        <span
            className={`inline-flex items-center rounded-lg px-2 py-1 text-xs font-semibold ${toneMap[tone] || toneMap.primary}`}
        >
            {children}
        </span>
    );
};

const RoadmapPage = () => {
    const meta = {
        version: "v1.0.1",
        lastUpdated: "2026-01-10",
        status: "로드맵 페이지 추가",
        tags: ["버그 픽스", "UI 개선", "UX 개선"],
        comment: "이번 업데이트로 EUNOIA에 로드맵 페이지가 새로 생성되었습니다.\n 앞으로 지속적인 업데이트와 아이디어를 여러분들과 공유할 수 있길 바라며 이용 중 건의사항을 전달주시면 적극적으로 반영하여 함께 개선해나갈 계획입니다. 감사합니다.",
    };

    const updates = [
        {
            date: "2026-01-10",
            version: "v1.0.1",
            keywords: ["로드맵 페이지 추가", "버그 픽스", "UI 개선", "UX 개선"],
            items: [
                "히스토리 & 개발 현황을 알 수 있는 로드맵 페이지 추가",
                "로그인 페이지에서 새로고침 시 스프링 로그인 페이지로 리다이렉팅 되던 현상 수정",
                "모바일 환경에서 영어 폰트가 깨지는 현상 수정",
                "모바일 환경( 아이폰 사파리 )에서 감정글 작성 시 화면이 줌인 되는 현상 수정",
                "회원가입 성공 시 너무 빨리 로그인 페이지로 리다이렉팅 되는 현상 수정",
            ],
            tone: "ok",
        },
        {
            date: "2026-01-09",
            version: "v1.0.0",
            keywords: ["배포 완료", "회원가입 안정화"],
            items: [
                "회원가입 → 승인(PENDING/ACTIVE) 플로우 정상 동작",
                "예외/응답 표준화(ApiResponse + ErrorCode) 정리 & 적용",
                "프론트 axios 에러 정규화 & 페이지 단 에러 처리 안정화",
            ],
            tone: "ok",
        },
        {
            date: "2026-01-05",
            version: "v0.9.9",
            keywords: ["전역 에러 처리", "로딩 UX 개선"],
            items: [
                "배포 전 스프링부트 컨트롤러 안정화",
                "api요청 로직 안정화",
                "배포 전 백엔드 & 프론트엔드 예외처리 표준화",
                "useApiError 패턴 도입",
                "모바일 환경 전용 화면 추가 + 모바일 전용 네비게이션 추가",
                "로딩 최소 유지 타이머 + 취소 가드 적용",
            ],
            tone: "ok",
        },
    ];


    const issues = [
        {
            title: "DB시퀀스 동기화문제",
            desc: "테이블마다 각각 존재하는 시퀀스가 동일하게 처리되는 문제",
            severity: "warn",
            hint: "JPA테이블간 조인관계에서 발생한 것으로 간주됨.\nJPA 엔티티 수정 중.",
        },
        {
            title: "감정점수 차트의 비정상적인 작동",
            desc: "DB시퀀스 동기화문제로 야기된 문제.\n차트 자체는 정상동작하나 내부 점수데이터의 손실.",
            severity: "ok",
            hint: "DB시퀀스 동기화문제 해결 후 추가 작업 예정.",
        },
        {
            title: "간헐적 로딩 지연 체감",
            desc: "설계 의도를 넘어서는 지연 현상이 간헐적으로 발견되는 문제.",
            severity: "",
            hint: "현재 확인 관찰 단계.",
        },
    ];

    const roadmap = [
        {
            quarter: "NEXT",
            title: "다음 업데이트 (예정)",
            items: [
                "배포에 따른 로직 안정화",
                "UI개선(버튼 색감, 카드 보더 등), 감정그래프 디자인",
                "모바일 환경 UX 보강",
            ],
        },
        {
            quarter: "SOON",
            title: "추가 예정인 서비스",
            items: [
                "추후 아이디어가 확정되면 선보이겠습니다.",
            ],
        },
    ];

    const backlog = [
        "출석/성장 시스템(새싹 → 나무 컨셉)",
        "테마 토글(감성/코드 느낌) 확장",
        "마음병편지(익명 롤링페이퍼)",
        "심리테스트",
    ];

    return (
        <div className="w-full">
            <div className="mx-auto w-full max-w-4xl px-4 sm:px-6 py-6 md:py-10">
                {/* 0) 헤더 요약 카드 */}
                <CardMotion index={0}>
                    <section className="rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8">
                        <p className="text-sm text-textSecondary mb-2">현황판</p>

                        <h1 className="text-2xl md:text-3xl font-bold text-textPrimary leading-snug">
                            EUNOIA 업데이트 & 히스토리
                        </h1>

                        <div className="mt-4 rounded-2xl bg-white/45 shadow-sm p-5 md:p-6">
                            <div className="flex flex-wrap items-center gap-2">
                                <Badge tone="ok">{meta.status}</Badge>
                                <Badge tone="neutral">{meta.version}</Badge>
                                <Badge tone="neutral">Last: {meta.lastUpdated}</Badge>
                            </div>

                            <div className="mt-3 flex flex-wrap gap-2">
                                {meta.tags.map((t) => (
                                    <Badge key={t} tone="primary">
                                        {t}
                                    </Badge>
                                ))}
                            </div>

                            <p className="mt-4 text-sm md:text-base leading-relaxed text-textSecondary whitespace-pre-line">
                                {meta.comment}
                            </p>
                        </div>
                    </section>
                </CardMotion>

                {/* 1) 업데이트 내역 */}
                <CardMotion index={1}>
                    <section className="mt-6 rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8">
                        <p className="text-sm text-textSecondary mb-2">업데이트 내역</p>
                        <div className="mt-2 max-h-[420px] md:max-h-[520px] overflow-y-auto pr-2">
                            <div className="space-y-4">
                                {updates.map((u) => (
                                    <div key={`${u.date}-${u.version}`} className="rounded-2xl bg-white/40 p-5">
                                        {/* 상단 메타 정보 */}
                                        <div className="md:flex md:items-center gap-2">
                                            <div className="flex flex-wrap items-center gap-2">
                                                <Badge tone="neutral">{u.date}</Badge>
                                                <Badge tone="neutral">{u.version}</Badge>
                                            </div>
                                            {/* 키워드 뱃지 */}
                                            <div className="mt-1 md:mt-0 flex flex-wrap items-center gap-1">
                                                {u.keywords.map((kw) => (
                                                    <Badge key={kw} tone={u.tone}>{kw}</Badge>
                                                ))}
                                            </div>
                                        </div>

                                        {/* 상세 내역 */}
                                        <ul className="mt-3 text-sm md:text-base text-textSecondary leading-relaxed list-disc pl-5 space-y-2">
                                            {u.items.map((it, idx) => (
                                                <li key={idx}>{it}</li>
                                            ))}
                                        </ul>
                                    </div>
                                ))}

                            </div>
                        </div>
                    </section>
                </CardMotion>


                {/* 2) 현재 확인 & 작업중인 현상 */}
                <CardMotion index={2}>
                    <section className="mt-6 rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8">
                        <p className="text-sm text-textSecondary mb-2">현재 확인 & 작업중인 현상</p>

                        <div className="mt-4 grid grid-cols-1  gap-4">
                            {issues.map((i, idx) => (
                                <div key={idx} className="rounded-2xl bg-surface/60 shadow-sm p-6">
                                    <div className="flex items-center justify-between gap-3">
                                        <h2 className="text-lg font-bold text-textPrimary">{i.title}</h2>
                                    </div>
                                    <Badge tone={i.severity}>
                                        {i.severity === "warn"
                                            ? "작업중"
                                            : i.severity === "danger"
                                                ? "긴급수정중"
                                                : i.severity === "ok"
                                                    ? "작업대기중"
                                                    : "원인파악중"}
                                    </Badge>

                                    <p className="mt-3 text-sm md:text-base leading-relaxed text-textSecondary whitespace-pre-line">
                                        {i.desc}
                                    </p>

                                    <div className="mt-4 rounded-xl bg-white/35 p-4">
                                        <p className="text-xs font-semibold text-textSecondary">작업현황</p>
                                        <p className="mt-1 text-sm leading-relaxed text-textSecondary whitespace-pre-line">{i.hint}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </section>
                </CardMotion>

                {/* 3) 로드맵 */}
                <CardMotion index={3}>
                    <section className="mt-6 rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8">
                        <p className="text-sm text-textSecondary mb-2">로드맵</p>

                        <div className="mt-4 space-y-4">
                            {roadmap.map((r, idx) => (
                                <div key={idx} className="rounded-2xl bg-white/40 p-5">
                                    <div className="flex flex-wrap items-center gap-2">
                                        <Badge tone="secondary">{r.quarter}</Badge>
                                        <h2 className="text-lg font-bold text-textPrimary">{r.title}</h2>
                                    </div>

                                    <ul className="mt-3 text-sm md:text-base text-textSecondary leading-relaxed list-disc pl-5 space-y-2">
                                        {r.items.map((it, i2) => (
                                            <li key={i2}>{it}</li>
                                        ))}
                                    </ul>
                                </div>
                            ))}
                        </div>
                    </section>
                </CardMotion>

                {/* 4) 아이디어 백로그 */}
                <CardMotion index={4}>
                    <section className="mt-6 rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8">
                        <p className="text-sm text-textSecondary mb-2">아이디어 백로그</p>

                        <div className="mt-4 rounded-2xl bg-white/40 p-5">
                            <p className="text-sm md:text-base leading-relaxed text-textSecondary">
                                현재 개발자의 머릿속
                            </p>

                            <div className="mt-4 flex flex-wrap gap-2">
                                {backlog.map((b) => (
                                    <Badge key={b} tone="neutral">
                                        {b}
                                    </Badge>
                                ))}
                            </div>
                        </div>
                    </section>
                </CardMotion>

                {/* 5) Footer note */}
                <CardMotion index={5}>
                    <section className="mt-6 rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8">
                        <h2 className="text-lg font-bold text-textPrimary">개발자의 마음</h2>
                        <p className="mt-3 text-sm md:text-base leading-relaxed text-textSecondary">
                            궁극적으로 여러분들과 함께 만드는 EUNOIA가 제 목표이기에 사용하시면서 개선점이나 새로운 아이디어는 언제든 제시해주신다면 적극 반영하겠습니다.
                        </p>
                        <p className="mt-4 text-xs text-textSecondary">© EUNOIA · 지리산개구리</p>
                    </section>
                </CardMotion>
            </div>
        </div>
    );
};

export default RoadmapPage;
