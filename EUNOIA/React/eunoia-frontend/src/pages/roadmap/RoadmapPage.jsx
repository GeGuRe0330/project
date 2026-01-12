import CardMotion from "../../components/motion/CardMotion";

// 뱃지 컴포넌트
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
            className={`inline-flex items-center rounded-lg px-2 py-1 text-xs font-semibold shadow-sm border-[1px] border-primary/70 ${toneMap[tone] || toneMap.primary}`}
        >
            {children}
        </span>
    );
};

const RoadmapPage = () => {
    const meta = {
        version: "v1.1.1",
        lastUpdated: "2026-01-12",
        status: "메인 대시보드 개편",
        tags: ["감정글 작성 유도 페이지 추가", "UI 개선", "UX 개선", "애니메이션 효과 추가", "버그 픽스"],
        comment: "이번 업데이트(v1.1.1)는 EUNOIA의 기능을 늘리기보다, ‘어떻게 읽히고, 어떻게 느껴지는가’에 집중한 UI·UX 전면 개편입니다.\n\n첫 배포 이후 예상보다 많은 분들이 서비스를 직접 사용해 주셨고, 그 과정에서 여러 소중한 피드백을 받을 수 있었습니다. 그중 가장 인상 깊었던 반응은 다음 질문들이었습니다.\n\n\"그래서, 이걸 어떻게 보면 되는 거야?\"\n\"뭐부터 읽어야 하지...?\"\n\n이 질문들을 통해 저는 분석 결과가 충분히 의미 있음에도 불구하고, 구조 없이 한 번에 밀려오면서 사용자에게 피로를 주고있다는 점을 깨달았습니다.\n\n이번 개편에서는 감정 흐름을 중심으로 정보의 우선순위를 재정의하고, ‘차트 → 요약 → 해석 → 메시지’로 자연스럽게 읽히는 흐름을 설계하는 데 집중했습니다.\n또한 첫 회원가입 이후 무엇을 해야 할지 막막하다는 의견을 반영하여, 서비스의 방향과 사용 흐름을 안내하는 가이드라인 페이지를 함께 추가했습니다. \n\nEUNOIA는 정답을 제시하는 서비스가 아니라, 스스로의 감정을 차분히 바라볼 수 있도록 돕는 관찰자이기를 원합니다. \n이번 업데이트가 그 방향에 한 걸음 더 가까워진 변화이길 바라며, 앞으로에 변화에도 여러분이 함께했으면 합니다.",
    };

    const updates = [
        {
            date: "2026-01-12",
            version: "v1.1.1",
            keywords: ["메인 대시보드 개편", "감정글 작성 유도 페이지 추가", "UI 개선", "UX 개선", "애니메이션 효과 추가", "버그 픽스"],
            items: [
                "메인 대시보드 개편",
                "\u00A0\u00A0\u00A0- 레이아웃 구조 변경 ( PC, 테블릿 PC 환경 )",
                "\u00A0\u00A0\u00A0- 문장 포멧터 로직 변경",
                "\u00A0\u00A0\u00A0- 컨텐츠 카드 UI 변경",
                "\u00A0\u00A0\u00A0- 감정 흐름 카드의 구조 & UI 변경",
                "\u00A0\u00A0\u00A0- [EUNOIA가 본 당신] 카드에 커튼 애니매이션 추가",
                "\u00A0\u00A0\u00A0- [EUNOIA가 본 당신] 카드 폰트를 손글씨 폰트로 변경",
                "나눔손글씨 나무정원 폰트 추가 (출처 : 네이버 폰트)",
                "최초 회원 가입 후 로그인 시 감정 기록 유도 컴포넌트 추가",
                "모바일 네비게이션 버튼 보더 수정",
                "모바일 네비게이션 애니매이션 추가",
                "분석 로딩페이지 UI 보더 라운드 추가",
                "[유노이아란?] 페이지 인스타그램 버튼에 스타일이 적용 안되던 현상 수정",
            ],
            tone: "ok",
        },
        {
            date: "2026-01-10",
            version: "v1.0.1",
            keywords: ["로드맵 페이지 추가", "버그 픽스", "UI 개선", "UX 개선"],
            items: [
                "히스토리 & 개발 현황을 알 수 있는 로드맵 페이지 추가",
                "로그인 페이지에서 새로고침 시 스프링 로그인 페이지로 리다이렉팅 되던 현상 수정",
                "분석 로직을 소폭 개선하여 분석시간 단축",
                "감정 차트의 비정상적인 점수 출력 문제 수정",
                "감정 차트 디자인 수정",
                "UI디자인 소폭 수정",
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
            title: "감정분석 로직 심화작업",
            desc: "유노이아가 비춰줄 여러분의 모습이 더욱 선명하게끔 만들고있어요.",
            severity: "warn",
            hint: "분석로직 파라미터 조정중.",
        },
        {
            title: "백엔드 DB저장 시간이 한국시간과 다른 현상",
            desc: "관련해서 감정 분석 점수 차트에 날짜가 실제 분석 시간과 차이가 생기는 현상.",
            severity: "",
            hint: "현재 확인 관찰 단계.",
        },
        {
            title: "간헐적 로딩 지연",
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
                "감정 분석 로직 심화 작업",
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
        "마음유리병편지(익명 롤링페이퍼)",
        "심리테스트",
        "치킨🍗",
        "피자🍕",
        "햄버거🍔",
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

                        <div className="mt-4 rounded-2xl bg-white/45 shadow-sm p-5 md:p-6 border-2 border-primary-dark/40">
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
                                    <div key={`${u.date}-${u.version}`} className="rounded-2xl bg-white/40 p-5 border-2 border-primary-dark/40">
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
                                <div key={idx} className="rounded-2xl bg-surface/60 shadow-sm p-6 border-2 border-primary-dark/40">
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

                                    <div className="mt-4 rounded-xl bg-white/80 p-4 border-[1px] border-primary-dark/20">
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
                                <div key={idx} className="rounded-2xl bg-white/40 p-5 border-2 border-primary-dark/40">
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
                    <section className="mt-6 rounded-2xl bg-surface/70 shadow-sm p-6 md:p-8 ">
                        <p className="text-sm text-textSecondary mb-2">아이디어 백로그</p>

                        <div className="mt-4 rounded-2xl bg-white/40 p-5 border-2 border-primary-dark/40">
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
                        <p className="mt-3 text-sm md:text-base leading-relaxed text-textSecondary whitespace-pre-line">
                            {"궁극적으로 여러분들과 함께 만드는 EUNOIA가 제 목표입니다.\n 사용하시면서 개선점이나 새로운 아이디어는 언제든 제시해주신다면 적극 반영할게요!"}
                        </p>
                        <p className="mt-4 text-xs text-textSecondary">© EUNOIA · 지리산개구리</p>
                    </section>
                </CardMotion>
            </div>
        </div>
    );
};

export default RoadmapPage;
