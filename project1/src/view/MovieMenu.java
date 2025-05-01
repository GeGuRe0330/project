package view;

public interface MovieMenu {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String WHITE = "\u001B[37m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GRAY = "\u001B[90m";

    public static final int MOVIELIST = 1;
    public static final int POPULAR = 2;
    public static final int UNPOPULAR = 3;
    public static final int RESERVATION = 4;
    public static final int CANCEL_RESERVATION = 5;
    public static final int CHECK_RESERVATION = 6;
    public static final int EDIT_RESERVATION = 7;
    public static final int WRITE_REVIEW = 8;
    public static final int LOOKUP_REVIEW = 9;
    public static final int EXIT = 10;

    public static void menuDisplay() {

        System.out.printf(YELLOW + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣄⡀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + RESET);
        System.out.printf("                          ███╗   ███╗███████╗███╗   ██╗██╗   ██╗\n");
        System.out.printf(YELLOW + "⠀⠀⠀⠀⠀⠀⠀⢠⣤⣤⣴⠿⢿⣿⣿⣿⣿⣆⣠⣤⣤⡀⢀⣀⠀⠀⠀⠀⠀⠀" + RESET);
        System.out.printf("                        ████╗ ████║██╔════╝████╗  ██║██║   ██║\n");
        System.out.printf(YELLOW + "⠀⠀⠀⠀⠀⣴⡆⢸⣿⡟⢡⣴⡀⠻⠿⠿⣿⣿⣿⣿⣿⣇⣈⣉⡁⠀⠀⠀⠀⠀" + RESET);
        System.out.printf("                        ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║\n");
        System.out.printf(YELLOW + "⠀⠀⠀⠀⣠⣤⣄⡈⠛⣿⣿⣿⣇⣤⣶⡆⢸⣿⣿⣿⡟⢋⣙⣻⡟⠀⠀⠀⠀⠀" + RESET);
        System.out.printf("                        ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║\n");
        System.out.printf(YELLOW + "⠀⠀⠀⠀⢿⣿⣿⣿⣄⣼⣿⣿⣿⣿⣿⣷⣾⡟⢡⣤⣤⣾⣿⣿⣶⡶⠀⠀⠀⠀" + RESET);
        System.out.printf("                        ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝\n");
        System.out.printf(GRAY + "⠀⠀⠀⠀⢀⣉⣉⣙⡛⠛⠛⠛⠛⠛⠛⠛⠛⠀⠚⠛⠛⢛⣋⣉⣉⡀⠀⠀⠀⠀" + RESET);
        System.out.printf("                        ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ \n");
        System.out.printf(RED + "⠀⠀⠀⠀⠘" + RED + "⣿⣿⣿⣿" + RED + "⡀" + RED + "⢸⣿⣿⣿⡇" + RED + "⢸⣿⣿⣿⡇" + RED + "⢀⣿⣿⣿⣿⠃\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀" + RED + "⢿" + RED + "⣿⣿⣿⡇" + RED + "⠸⠛⠋⠉⠉⠉⠉⠙⠛⠇" + RED + "⢸⣿⣿⣿⡿" + RESET);
        System.out.printf("       1. 상영 영화 리스트       2. 상영 영화 순위(인기순)       3. 상영 영화 순위(비인기순)\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀" + RED + "⢸" + RED + "⣿⣿⣿⠃" + WHITE + "⣰⣾⣿⣿⣿⣿⣿⣿⣷⣆" + RED + "⠘⣿⣿⣿⡇\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀⠀" + RED + "⣿⣿⣿⡀" + WHITE + "⢻⣿⣿⣿⣿⣿⣿⣿⣿⡟" + RED + "⢀⣿⣿⣿" + RESET);
        System.out.printf("        4. 예매하기               5. 예매 취소하기                6. 예매 내역 확인하기\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀⠀" + RED + "⢹⣿⣿⣷" + WHITE + "⠀⠉⠉⠛⠛⠛⠛⠉⠉" + RED + "⠀⣾⣿⣿⡏\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀⠀" + RED + "⠘⣿⣿⣿⡄" + RED + "⢹⣿⣿⡇⢸⣿⣿⡏" + RED + "⢠⣿⣿⣿⠃" + RESET);
        System.out.printf("        7. 예매 내역 수정하기     8. 리뷰 쓰기                    9. 리뷰 보기\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀⠀⠀" + RED + "⢻⣿⣿⡇" + RED + "⢸⣿⣿⡇⢸⣿⣿⡇" + RED + "⢸⣿⣿⡟\n");
        System.out.printf(WHITE + "⠀⠀⠀⠀⠀⠀⠀" + RED + "⠸⣿⣿⡇" + RED + "⢸⣿⣿⡇⢸⣿⣿⡇" + RED + "⢸⣿⣿⠇" + RESET);
        System.out.printf("                                   10. 시스템 종료                   \n");
        System.out.printf(GRAY + "⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠈⠉⠉⠁⠈⠉⠉⠁⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀" + RESET + "\n");
    }
}
