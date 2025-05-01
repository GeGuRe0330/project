package view;

public interface AdminMenu {
    public static final String GRAY = "\u001B[90m";
    public static final String RESET = "\u001B[0m";

    public static final int REGISTRATION_MOVIE = 1;
    public static final int MODIFY_MOVIE = 2;
    public static final int DELETE_MOVIE = 3;
    public static final int CHECK_RESERVATION = 4;
    public static final int EXIT = 5;

    public static void adminActive() {
        // 점차적으로 출력되는 문자열들
        String[] steps = {
                "_", "", "_", "ㄱ_", "고_", "과_", "관_", "관ㄹ_", "관리_", "관리ㅈ_", "관리자_",
                "관리자 _", "관리자 ㅋ_", "관리자 코_", "관리자 코ㄷ_", "관리자 코드_", "관리자 코드 _",
                "관리자 코드 ㅇ_", "관리자 코드 이_", "관리자 코드 입_", "관리자 코드 입ㄹ_",
                "관리자 코드 입려_", "관리자 코드 입력_", "관리자 코드 입력._",
                "관리자 코드 입력. ㄱ_", "관리자 코드 입력. 고_", "관리자 코드 입력. 과_",
                "관리자 코드 입력. 관_", "관리자 코드 입력. 관ㄹ_", "관리자 코드 입력. 관리_",
                "관리자 코드 입력. 관리ㅈ_", "관리자 코드 입력. 관리자_", "관리자 코드 입력. 관리자 _",
                "관리자 코드 입력. 관리자 ㅁ_", "관리자 코드 입력. 관리자 모_", "관리자 코드 입력. 관리자 모ㄷ_",
                "관리자 코드 입력. 관리자 모드_", "관리자 코드 입력. 관리자 모드ㄹ_", "관리자 코드 입력. 관리자 모드로_",
                "관리자 코드 입력. 관리자 모드로 _", "관리자 코드 입력. 관리자 모드로 ㅈ_", "관리자 코드 입력. 관리자 모드로 저_",
                "관리자 코드 입력. 관리자 모드로 전_", "관리자 코드 입력. 관리자 모드로 전ㅎ_",
                "관리자 코드 입력. 관리자 모드로 전호_", "관리자 코드 입력. 관리자 모드로 전화_",
                "관리자 코드 입력. 관리자 모드로 전환_", "관리자 코드 입력. 관리자 모드로 전환ㅎ_",
                "관리자 코드 입력. 관리자 모드로 전환하_", "관리자 코드 입력. 관리자 모드로 전환합_",
                "관리자 코드 입력. 관리자 모드로 전환합ㄴ_", "관리자 코드 입력. 관리자 모드로 전환합니_",
                "관리자 코드 입력. 관리자 모드로 전환합니ㄷ_", "관리자 코드 입력. 관리자 모드로 전환합니다_",
        };

        // 각 단계마다 출력하고 클리어
        for (String step : steps) {
            clear();
            System.out.println(step);
            try {
                Thread.sleep(20);
                if (step.equals("관리자 코드 입력._")) {
                    Thread.sleep(700);
                }
            } catch (InterruptedException e) {
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        for (int i = steps.length - 1; i >= 0; i--) {
            clear();
            System.out.println(steps[i]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
        clear();

    }

    public static void adminMenuDisplay() {
        System.out.printf(GRAY + "⠀⠀⠀⠀⢀⣀⣤⣤⣤⣤⣄⡀⠀⠀⠀⠀" + RESET);
        System.out.printf("          █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗\n");
        System.out.printf(GRAY + "⠀⢀⣤⣾⣿⣾⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀" + RESET);
        System.out.printf("         ██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║\n");
        System.out.printf(GRAY + "⢠⣾⣿⢛⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀" + RESET);
        System.out.printf("         ███████║██║  ██║██╔████╔██║██║██╔██╗ ██║\n");
        System.out.printf(GRAY + "⣾⣯⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧" + RESET);
        System.out.printf("         ██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║\n");
        System.out.printf(GRAY + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿" + RESET);
        System.out.printf("         ██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║\n");
        System.out.printf(GRAY + "⣿⡿⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠻⢿⡵" + RESET);
        System.out.printf("         ╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝\n");
        System.out.printf(GRAY + "⣿⡇⠀⠀⠉⠛⠛⣿⣿⠛⠛⠉⠀⠀⣿⡇\n");
        System.out.printf(GRAY + "⣿⣿⣀⠀⢀⣠⣴⡇⠹⣦⣄⡀⠀⣠⣿⡇" + RESET);
        System.out.printf("         1. 영화 등록               2. 영화 정보 수정\n");
        System.out.printf(GRAY + "⠋⠻⠿⠿⣟⣿⣿⣦⣤⣼⣿⣿⠿⠿⠟⠀" + RESET);
        System.out.printf("         3. 영화 삭제               4. 예매 현황 확인\n");
        System.out.printf(GRAY + "⠀⠀⠀⠀⠸⡿⣿⣿⢿⡿⢿⠇⠀⠀⠀⠀" + RESET);
        System.out.printf("         5. 종료      \n");
        System.out.printf(GRAY + "⠀⠀⠀⠀⠀⠀⠈⠁⠈⠁⠀⠀⠀⠀⠀⠀"+ RESET +"\n");

    }

    public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("터미널 클리어 실패 (OS: " + os + ")");
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }
}
