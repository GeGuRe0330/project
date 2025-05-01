package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import comparator.RsComparator;
import model.MovieData;
import model.ReservationData;
import model.ReviewData;

public class MovieMaterialize implements MovieInterface {
    public static String mtTitle;
    public static String resTitle;
    public static String rvTitle;

    // 데이터 등록
    @Override
    public void movieTableLoad(ArrayList<MovieData> mvList) {
        FileInputStream fi;

        try {
            fi = new FileInputStream("res/movieTable.txt");
            Scanner _scan = new Scanner(fi);

            if (_scan.hasNextLine()) {
                mtTitle = _scan.nextLine();
            }
            while (true) {
                if (!_scan.hasNextLine()) {
                    break;
                }
                String data = _scan.nextLine();
                String[] tokens = data.split(",");
                int movieNum = Integer.parseInt(tokens[0]);
                String movieName = tokens[1];
                String releaseDate = tokens[2];
                int reservationCount = Integer.parseInt(tokens[3]);

                MovieData md = new MovieData(movieNum, movieName, releaseDate, reservationCount);
                mvList.add(md);
            }

            System.out.println("영화 정보 로딩 / 초가화 완료!");
            Thread.sleep(1500);

            _scan.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("영화 정보를 불러오지 못했습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("불러오는 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void resTableLoad(ArrayList<ReservationData> rsList) {
        FileInputStream fi;

        try {
            fi = new FileInputStream("res/reservationInfoTable.txt");
            Scanner _scan = new Scanner(fi);

            if (_scan.hasNextLine()) {
                resTitle = _scan.nextLine();
            }
            while (true) {
                if (!_scan.hasNextLine()) {
                    break;
                }
                String data = _scan.nextLine();
                String[] tokens = data.split(",");
                String phoneNum = tokens[0];
                String userName = tokens[1];
                String movieName = tokens[2];
                int seatNum = Integer.parseInt(tokens[3]);

                ReservationData rvData = new ReservationData(phoneNum, userName, movieName, seatNum);
                rsList.add(rvData);
            }

            System.out.println("예매 정보 로딩 / 초가화 완료!");
            Thread.sleep(1500);

            _scan.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("예매 정보를 불러오지 못했습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("불러오는 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void reviewTableLoad(ArrayList<ReviewData> rvList) {
        FileInputStream fi;

        try {
            fi = new FileInputStream("res/reviewTable.txt");
            Scanner _scan = new Scanner(fi);

            if (_scan.hasNextLine()) {
                rvTitle = _scan.nextLine();
            }
            while (true) {
                if (!_scan.hasNextLine()) {
                    break;
                }
                String data = _scan.nextLine();
                String[] tokens = data.split(",");
                int reviewNum = Integer.parseInt(tokens[0]);
                int movieNum = Integer.parseInt(tokens[1]);
                double reviewRate = Double.parseDouble(tokens[2]);
                String comment = tokens[3];

                ReviewData rvData = new ReviewData(reviewNum, movieNum, reviewRate, comment);
                rvList.add(rvData);
            }

            System.out.println("리뷰 정보 로딩 / 초가화 완료!");

            Thread.sleep(1500);

            _scan.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("리뷰 정보를 불러오지 못했습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("불러오는 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void movieTableSave(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList) {
        try {
            // MovieData[reservationCount] 최신화
            for (MovieData data : mvList) {
                int count = 0;
                String mName = data.getMovieName();
                for (ReservationData resData : rsList) {
                    if (mName.equals(resData.getMovieName())) {
                        count++;
                    }
                }
                data.setReservationCount(count);
            }
            FileOutputStream fo = new FileOutputStream("res/movieTable.txt");
            PrintWriter writer = new PrintWriter(fo);

            writer.println(mtTitle);

            for (int i = 0; i < mvList.size(); i++) {
                MovieData movie = mvList.get(i);
                writer.print(movie.getMovieNum() + "," + movie.getMovieName() + "," + movie.getReleaseDate() + ","
                        + movie.getReservationCount());
                if (i < mvList.size() - 1) {
                    writer.println();
                }
            }

            System.out.println("영화 정보 저장 완료!");
            Thread.sleep(1500);
            writer.close();
            fo.close();
        } catch (FileNotFoundException e) {
            System.out.println("영화 정보를 저장할 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일 입출력 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void resTableSave(ArrayList<ReservationData> rsList) {
        try {
            FileOutputStream fo = new FileOutputStream("res/reservationInfoTable.txt");
            PrintWriter writer = new PrintWriter(fo);

            writer.println(resTitle);

            for (int i = 0; i < rsList.size(); i++) {
                ReservationData reservation = rsList.get(i);
                writer.print(reservation.getPhoneNum() + "," + reservation.getUserName() + ","
                        + reservation.getMovieName() + "," + reservation.getSeatNum());
                if (i < rsList.size() - 1) {
                    writer.println();
                }
            }

            System.out.println("예매 정보 저장 완료!");
            Thread.sleep(1500);
            writer.close();
            fo.close();
        } catch (FileNotFoundException e) {
            System.out.println("예매 정보를 저장할 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일 입출력 중 오류가 발생했습니다.");
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void reviewTableSave(ArrayList<ReviewData> rvList) {
        try {
            FileOutputStream fo = new FileOutputStream("res/reviewTable.txt");
            PrintWriter writer = new PrintWriter(fo);

            writer.println(rvTitle);

            for (int i = 0; i < rvList.size(); i++) {
                ReviewData review = rvList.get(i);
                writer.print(review.getReviewNum() + "," + review.getMovieNum() + "," + review.getReviewRate() + ","
                        + review.getComment());
                if (i < rvList.size() - 1) {
                    writer.println();
                }
            }

            System.out.println("리뷰 정보 저장 완료!");
            Thread.sleep(1500);
            writer.close();
            fo.close();
        } catch (FileNotFoundException e) {
            System.out.println("리뷰 정보를 저장할 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("파일 입출력 중 오류가 발생했습니다.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    // 관리자 메소드
    @Override
    public void movieRegistration(ArrayList<MovieData> mvList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|      개봉 영화 등록 : 상영할 영화를 시스템에 등록합니다.      |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            String movieName = getValidInput(
                    "등록할 영화 제목을 입력해주세요. > ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "올바른 영화 제목을 입력해주세요 (특수문자 제외, 최대 50자).",
                    scan);

            String releaseDate = getValidInput(
                    "등록할 영화의 개봉일을 입력해주세요 (예: 2025-04-27). > ",
                    "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
                    "날짜 형식이 올바르지 않습니다. 예: 2025-04-27",
                    scan);

            int max = Integer.MIN_VALUE;
            for (MovieData data : mvList) {
                if (max < data.getMovieNum()) {
                    max = data.getMovieNum();
                }
            }
            MovieData data = new MovieData(max + 1, movieName, releaseDate, 0);

            mvList.add(data);
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|               해당 영화가 개봉 처리 되었습니다!               |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            System.out.printf("등록한 영화 제목 : %10s      |       개봉일자 : %10s", data.getMovieName(), data.getReleaseDate());
            Thread.sleep(3000);
            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void movieModify(ArrayList<MovieData> mvList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|       영화 정보 수정 : 등록된 영화의 정보를 수정합니다.       |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            String inputName = getValidInput(
                    "정보를 수정할 영화의 제목을 입력해주세요 > ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "올바른 영화 제목을 입력해주세요. (한글/영문/숫자/공백 허용, 특수문자 제외)",
                    scan);

            MovieData findMovieData = null;
            for (MovieData data : mvList) {
                if (data.getMovieName().equals(inputName)) {
                    findMovieData = data;
                    break;
                }
            }

            if (findMovieData == null) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   해당 영화는 미개봉상태입니다. (error : %s)   \n", inputName);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                Thread.sleep(3000);
                clear();
                return;
            }

            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.printf("\n   선택된 영화 정보 : %s\n\n   잠시후 수정페이지로 넘어갑니다. . .\n\n", findMovieData.getMovieName());
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            Thread.sleep(3000);
            clear();

            String movieName = getValidInput(
                    "수정할 영화 제목 : %s => ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "영화 제목이 유효하지 않습니다. 다시 입력해주세요.",
                    scan,
                    findMovieData.getMovieName());

            String releaseDate = getValidInput(
                    "수정할 영화 개봉일 : %s => ",
                    "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
                    "날짜 형식이 잘못되었습니다. 예: 2025-04-27",
                    scan,
                    findMovieData.getReleaseDate());

            findMovieData.setMovieName(movieName);
            findMovieData.setReleaseDate(releaseDate);
            clear();

            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.printf("\n       정상적으로 수정이 완료되었습니다!\n\n");
            System.out.printf("    영화 제목 : %s  개봉일 : %s\n\n", findMovieData.getMovieName(),
                    findMovieData.getReleaseDate());
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            Thread.sleep(3000);
            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void movieDelete(ArrayList<MovieData> mvList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|           영화 정보 삭제 : 등록된 영화를 삭제합니다.          |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            String inputName = getValidInput(
                    "삭제할 영화의 제목을 입력해주세요 > ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "영화 제목이 유효하지 않습니다. 다시 입력해주세요. (1~50자, 특수문자 제외)",
                    scan);

            MovieData findMovieData = null;
            for (MovieData data : mvList) {
                if (data.getMovieName().equals(inputName)) {
                    findMovieData = data;
                    break;
                }
            }

            if (findMovieData == null) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   해당 영화정보가 존재하지 않습니다. (error : %s)   \n", inputName);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

                Thread.sleep(3000);
                clear();
                return;
            }

            mvList.remove(findMovieData);
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.printf("\n     삭제 처리 완료되었습니다. \n     영화 제목 : %s\n\n", findMovieData.getMovieName());
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            Thread.sleep(3000);

            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void checkMovieRank(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan) {
        clear();
        System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
        System.out.println("|       예매 현황 : 현재 예매가 가장 많은/가장 적은 영화.       |");
        System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
        for (MovieData data : mvList) {
            int count = 0;
            String mName = data.getMovieName();
            for (ReservationData resData : rsList) {
                if (mName.equals(resData.getMovieName())) {
                    count++;
                }
            }
            data.setReservationCount(count);
        }

        int max = Integer.MIN_VALUE;

        MovieData maxData = null;
        for (MovieData data : mvList) {
            if (max < data.getReservationCount()) {
                maxData = data;
                max = data.getReservationCount();
            }
        }

        int min = Integer.MAX_VALUE;

        MovieData minData = null;
        for (MovieData data : mvList) {
            if (min > data.getReservationCount()) {
                minData = data;
                min = data.getReservationCount();
            }
        }

        System.out.printf("   가장 예매가 많은 영화 : %s | (총 예매 : %d 개)\n", maxData.getMovieName(),
                maxData.getReservationCount());
        System.out.printf("   가장 예매가 적은 영화 : %s | (총 예매 : %d 개)\n", minData.getMovieName(),
                minData.getReservationCount());
        System.out.println("=================================================================");
        System.out.print("\n계속하려면 엔터키를 눌러주세요. . .");
        scan.nextLine();
        clear();
    }

    // 사용자 메소드

    // 영화 순위

    @Override
    public void checkMovieList(ArrayList<MovieData> mvList, Scanner scan) {
        clear();
        System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
        System.out.println("|                       현재 개봉중인 영화                      |");
        System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
        for (MovieData data : mvList) {
            System.out.printf("  영화명 : %-6s   개봉일 : %-6s   남은 좌석 수 : %d\n", data.getMovieName(), data.getReleaseDate(),
                    (50 - data.getReservationCount()));
        }
        System.out.print("\n계속하려면 엔터키를 눌러주세요. . .");
        scan.nextLine();
        clear();
    }

    @Override
    public void sortMovieList(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan) {
        clear();
        System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
        System.out.println("|       예매 순위 (하위권) : 현재 예매가 가장 적은 영화.        |");
        System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
        // MovieData[reservationCount] 최신화
        for (MovieData data : mvList) {
            int count = 0;
            String mName = data.getMovieName();
            for (ReservationData resData : rsList) {
                if (mName.equals(resData.getMovieName())) {
                    count++;
                }
            }
            data.setReservationCount(count);
        }

        Collections.sort(mvList, new RsComparator(true));

        System.out.printf("          ======== 영화별 예매 순위표 (하위) ========\n\n");
        int rankCount = 1;
        for (MovieData data : mvList) {
            System.out.printf("                 %d위 : %s ( 총 예매 %d개)\n\n", rankCount, data.getMovieName(),
                    data.getReservationCount());
            rankCount++;
        }
        System.out.printf("\n          ===========================================");
        System.out.print("\n계속하려면 엔터키를 눌러주세요. . .");
        scan.nextLine();
        clear();
    }

    @Override
    public void dessortMovieList(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan) {
        clear();
        System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
        System.out.println("|       예매 순위 (상위권) : 현재 예매가 가장 많은 영화.        |");
        System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

        // MovieData[reservationCount] 최신화
        for (MovieData data : mvList) {
            int count = 0;
            String mName = data.getMovieName();
            for (ReservationData resData : rsList) {
                if (mName.equals(resData.getMovieName())) {
                    count++;
                }
            }
            data.setReservationCount(count);
        }

        Collections.sort(mvList, new RsComparator(false));

        System.out.printf("          ======== 영화별 예매 순위표 (하위) ========\n\n");
        int rankCount = 1;
        for (MovieData data : mvList) {
            System.out.printf("                 %d위 : %s ( 총 예매 %d개)\n\n", rankCount, data.getMovieName(),
                    data.getReservationCount());
            rankCount++;
        }
        System.out.printf("\n          ===========================================");
        System.out.print("\n계속하려면 엔터키를 눌러주세요. . .");
        scan.nextLine();
        clear();
    }

    // 영화 예매

    @Override
    public void movieReservation(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|                        영화 예매 페이지                       |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            String phoneNum = getValidInput(
                    "예매자의 전화번호를 입력해주세요. > ",
                    "^010-\\d{4}-\\d{4}$",
                    "올바른 전화번호 형식이 아닙니다. 다시 입력해주세요. (예: 010-1234-5678)",
                    scan);

            String userName = getValidInput(
                    "예매자의 이름을 입력해주세요. > ",
                    "^[a-zA-Z가-힣]{1,50}$",
                    "이름은 한글 또는 영문만 입력 가능합니다. 다시 입력해주세요.",
                    scan);

            String movieName = getValidInput(
                    "예매할 영화의 제목을 입력해주세요. > ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "영화 제목은 1~50자 이내로 입력해주세요. 특수문자는 사용하지 마세요.",
                    scan);

            // 개봉중인 영화인지 확인
            boolean trueMovie = false;
            for (MovieData data : mvList) {
                if (movieName.equals(data.getMovieName())) {
                    trueMovie = true;
                }
            }
            if (trueMovie == false) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   예매할 영화 제목을 다시 확인해주세요. 입력값 : %s\n", movieName);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                Thread.sleep(3000);
                clear();
                return;
            }

            clear();

            // 영화 좌석 초기화
            ArrayList<Integer> totalSeat = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                totalSeat.add(i + 1);
            }

            // 이미 예매된 좌석 제거
            for (ReservationData data : rsList) {
                if (data.getMovieName().equals(movieName)) {
                    totalSeat.set(data.getSeatNum() - 1, 0);
                }
            }

            int seatNum = 0;
            while (true) {
                boolean checkSeat = false;
                System.out.printf("╭─────────────────────────────────────────────────────.★..─╮\n\n");
                System.out.printf("        %s의 좌석 현황 (0은 이미 예매된 자리)\n\n", movieName);
                System.out.printf("╰─..★.─────────────────────────────────────────────────────╯\n\n");

                System.out.printf("========================== 스크린 =========================\n\n", movieName);
                for (int i = 0; i < 50; i++) {
                    if (totalSeat.get(i) == 0) {
                        System.out.printf("\033[31m [0%d] \033[0m", totalSeat.get(i));
                    } else if (0 < totalSeat.get(i) && totalSeat.get(i) < 10) {
                        System.out.printf(" [0%d] ", totalSeat.get(i));
                    } else {
                        System.out.printf(" [%-2d] ", totalSeat.get(i));
                    }
                    if ((i + 1) % 10 == 0) {
                        System.out.printf("\n\n");
                    }
                }

                // 해당 좌석이 예약좌석이 아닌지 확인
                seatNum = Integer.parseInt(getValidInput(
                        "예매할 좌석 번호를 입력해주세요. > ",
                        "^[1-9][0-9]?$|^50$",
                        "유효한 좌석 번호가 아닙니다. 다시 입력해주세요. (1~50)",
                        scan));

                for (int i = 0; i < totalSeat.size(); i++) {
                    if (totalSeat.get(i) == seatNum) {
                        checkSeat = true;
                    }
                }

                if (checkSeat) {
                    break;
                } else {
                    System.out.println("이미 예약된 좌석입니다. 다시 입력해주세요.");
                }
                Thread.sleep(2000);
                clear();
            }

            ReservationData rsData = new ReservationData(phoneNum, userName, movieName, seatNum);

            rsList.add(rsData);
            clear();

            System.out.printf(
                    "╭───────────────────────────────────────────────────────────────────────────────────────────────.★..─╮\n\n");
            System.out.printf("                              정상적으로 예매 처리 되었습니다!\n\n");
            System.out.printf("     예매 정보 | 전화 번호 : %s, 예매자 : %s, 영화 : %s, 좌석번호 : %d\n\n", phoneNum, userName,
                    movieName, seatNum);
            System.out.println(
                    "╰─..★.───────────────────────────────────────────────────────────────────────────────────────────────╯");

            Thread.sleep(3000);
            clear();

        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void cancelReservation(ArrayList<ReservationData> rsList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|                        예매 취소 페이지                       |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            String inputNum = getValidInput(
                    "예매자 전화번호를 입력해주세요(-도 같이 입력) > ",
                    "^\\d{3}-\\d{3,4}-\\d{4}$",
                    "유효한 전화번호가 아닙니다. (형식: 010-1234-5678)",
                    scan);

            ReservationData findMovieData = null;
            Iterator<ReservationData> iterator = rsList.iterator();
            while (iterator.hasNext()) {
                ReservationData data = iterator.next();
                if (data.getPhoneNum().equals(inputNum)) {
                    findMovieData = data;
                    iterator.remove();
                    break;
                }
            }

            if (findMovieData == null) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   해당 예약정보가 존재하지 않습니다. (error : %s)\n", inputNum);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                Thread.sleep(3000);
                clear();
                return;
            }

            System.out.printf("╭──────────────────────────────────────────────────────────.★..─╮\n\n");
            System.out.printf("   삭제 처리 완료되었습니다. 이름 : %s  |  영화 : %s\n\n", findMovieData.getUserName(),
                    findMovieData.getMovieName());
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            Thread.sleep(3000);

            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void checkReservation(ArrayList<ReservationData> rsList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|                        예매 확인 페이지                       |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            String inputNum = getValidInput(
                    "예매자 전화번호를 입력해주세요(-도 같이 입력) > ",
                    "^\\d{3}-\\d{3,4}-\\d{4}$",
                    "유효한 전화번호가 아닙니다. (형식: 010-1234-5678)",
                    scan);

            ArrayList<ReservationData> findMovieDataList = new ArrayList<>();
            for (ReservationData data : rsList) {
                if (data.getPhoneNum().equals(inputNum)) {
                    findMovieDataList.add(data);
                }
            }

            if (findMovieDataList.isEmpty()) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   해당 예약정보가 존재하지 않습니다. (error : %s)\n", inputNum);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                Thread.sleep(3000);
                clear();
                return;
            }

            clear();

            System.out.printf("╭──────────────────────────────────────────────────────────.★..─╮\n\n");
            System.out.printf("     %s님의 예매 현황입니다.\n", findMovieDataList.get(0).getUserName());
            for (ReservationData data : findMovieDataList) {
                System.out.printf("   영화: %s, 좌석번호: %d, 예매자: %s\n",
                        data.getMovieName(), data.getSeatNum(), data.getUserName());
            }
            System.out.printf("\n╰─..★.──────────────────────────────────────────────────────────╯\n\n");

            System.out.println("계속하려면 엔터를 눌러주세요.");
            scan.nextLine();

            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void editReservation(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|                        예매 수정 페이지                       |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            String inputNum = getValidInput(
                    "예매자 전화번호를 입력해주세요(-도 같이 입력) > ",
                    "^\\d{3}-\\d{3,4}-\\d{4}$",
                    "유효한 전화번호가 아닙니다. (형식: 010-1234-5678)",
                    scan);

            ArrayList<ReservationData> findMovieDataList = new ArrayList<>();
            for (ReservationData data : rsList) {
                if (data.getPhoneNum().equals(inputNum)) {
                    findMovieDataList.add(data);
                }
            }

            if (findMovieDataList.isEmpty()) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   해당 예약정보가 존재하지 않습니다. (error : %s)\n", inputNum);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

                Thread.sleep(3000);
                clear();
                return;
            }
            System.out.printf("╭──────────────────────────────────────────────────────────.★..─╮\n\n");

            System.out.println("   어느 예약을 수정하시겠습니까?");
            System.out.println("    ───────────────────────────────────────────────────────");
            int count = 0;
            for (ReservationData data : findMovieDataList) {
                System.out.printf("     %d | 예매자 : %s 영화제목 : %s 좌석번호 %d\n",
                        count + 1, data.getUserName(), data.getMovieName(), data.getSeatNum());
                count++;
            }
            System.out.printf("\n╰─..★.──────────────────────────────────────────────────────────╯\n");

            int selectNum = -1;
            while (true) {
                try {
                    System.out.print("수정할 예약을 선택하세요 (번호 입력): ");
                    selectNum = Integer.parseInt(scan.nextLine()) - 1;
                    if (selectNum >= 0 && selectNum < findMovieDataList.size()) {
                        break;
                    } else {
                        System.out.println("유효하지 않은 번호입니다. 다시 입력해주세요.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("숫자만 입력 가능합니다. 다시 입력해주세요.");
                }
            }
            clear();

            ReservationData selecData = findMovieDataList.get(selectNum);

            String phoneNum = getValidInput(
                    "수정할 예매자의 전화번호를 입력해주세요.\n %s > ",
                    "^\\d{3}-\\d{3,4}-\\d{4}$",
                    "유효한 전화번호가 아닙니다. (형식: 010-1234-5678)",
                    scan,
                    selecData.getPhoneNum());

            String userName = getValidInput(
                    "수정할 예매자의 이름을 입력해주세요.\n %s > ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "유효한 이름이 아닙니다. (특수문자 불가)",
                    scan,
                    selecData.getUserName());

            String movieName = getValidInput(
                    "수정할 예매할 영화 제목을 입력해주세요.\n %s > ",
                    "^[\\w가-힣\\s]{1,50}$",
                    "유효한 영화 제목이 아닙니다. (특수문자 불가)",
                    scan,
                    selecData.getMovieName());

            // 개봉중인 영화인지 확인
            boolean trueMovie = false;
            for (MovieData data : mvList) {
                if (movieName.equals(data.getMovieName())) {
                    trueMovie = true;
                }
            }
            if (trueMovie == false) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   예매할 영화 제목을 다시 확인해주세요. 입력값 : %s\n", movieName);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

                Thread.sleep(3000);
                return;
            }

            clear();

            // 영화 좌석 초기화
            ArrayList<Integer> totalSeat = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                totalSeat.add(i + 1);
            }

            // 이미 예매된 좌석 제거
            for (ReservationData data : rsList) {
                if (data.getMovieName().equals(movieName)) {
                    totalSeat.set(data.getSeatNum() - 1, 0);
                }
            }

            int seatNum = 0;
            while (true) {
                boolean checkSeat = false;
                System.out.printf("╭─────────────────────────────────────────────────────.★..─╮\n\n");
                System.out.printf("        %s의 좌석 현황 (0은 이미 예매된 자리)\n\n", movieName);
                System.out.printf("╰─..★.─────────────────────────────────────────────────────╯\n\n");

                System.out.printf("========================== 스크린 =========================\n\n", movieName);
                for (int i = 0; i < 50; i++) {
                    if (totalSeat.get(i) == 0) {
                        System.out.printf("\033[31m [0%d] \033[0m", totalSeat.get(i));
                    } else if (0 < totalSeat.get(i) && totalSeat.get(i) < 10) {
                        System.out.printf(" [0%d] ", totalSeat.get(i));
                    } else {
                        System.out.printf(" [%-2d] ", totalSeat.get(i));
                    }
                    if ((i + 1) % 10 == 0) {
                        System.out.printf("\n\n");
                    }
                }

                // 해당 좌석이 예약좌석이 아닌지 확인
                seatNum = Integer.parseInt(getValidInput(
                        "예매할 좌석 번호를 입력해주세요. > ",
                        "^[1-9][0-9]?$|^50$",
                        "유효한 좌석 번호가 아닙니다. 다시 입력해주세요. (1~50)",
                        scan));

                for (int i = 0; i < totalSeat.size(); i++) {
                    if (totalSeat.get(i) == seatNum) {
                        checkSeat = true;
                    }
                }

                if (checkSeat) {
                    break;
                } else {
                    System.out.println("이미 예약된 좌석입니다. 다시 입력해주세요.");
                }
                Thread.sleep(2000);
                clear();
            }

            selecData.setPhoneNum(phoneNum);
            selecData.setUserName(userName);
            selecData.setMovieName(movieName);
            selecData.setSeatNum(seatNum);

            clear();

            System.out.printf(
                    "╭───────────────────────────────────────────────────────────────────────────────────────────────.★..─╮\n\n");
            System.out.printf("                              정상적으로 예매 수정 되었습니다!\n\n");
            System.out.printf("   수정된 예매 정보 | 전화 번호 : %s, 예매자 : %s, 영화 : %s, 좌석번호 : %d\n\n", phoneNum, userName,
                    movieName, seatNum);
            System.out.println(
                    "╰─..★.───────────────────────────────────────────────────────────────────────────────────────────────╯");
            Thread.sleep(3000);
            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void writeReview(ArrayList<MovieData> mvList, ArrayList<ReviewData> rvList, Scanner scan) {
        try {
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.println("|                        리뷰 등록 페이지                       |");
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
            String movieName = getValidInput(
                    "리뷰할 영화 제목을 입력해주세요. > ",
                    "^[\\w가-힣\\s]+$",
                    "유효한 영화 제목이 아닙니다. (영어, 한글, 공백만 허용)",
                    scan);

            MovieData findMovieData = null;
            int movieNum = 0;
            for (MovieData data : mvList) {
                if (data.getMovieName().equals(movieName)) {
                    findMovieData = data;
                    movieNum = data.getMovieNum();
                    break;
                }
            }

            if (findMovieData == null) {
                clear();
                System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                System.out.printf("   해당 영화는 미개봉상태입니다. (error : %s)\n", movieName);
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                Thread.sleep(3000);
                clear();
                return;
            }

            double reviewRate = 0.0;
            while (true) {
                reviewRate = Double.parseDouble(getValidInput(
                        "평점을 매겨주세요 ( 0.0 ~ 5.0 )",
                        "^([1-5](\\.\\d)?)$",
                        "유효한 평점 값(숫자)을 입력해주세요.",
                        scan));
                if (5 < reviewRate) {
                    clear();
                    System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                    System.out.println("         지정된 범위의 평점을 입력해주세요.");
                    System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                    Thread.sleep(3000);
                    clear();
                    return;
                } else {
                    break;
                }

            }

            String inputComment = null;
            while (true) {
                System.out.print("리뷰를 작성해주세요. > ");
                inputComment = scan.nextLine();
                if (inputComment.trim().isEmpty()) {
                    clear();
                    System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
                    System.out.println("    리뷰는 공백일 수 없습니다. 다시 입력해주세요.");
                    System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");
                    Thread.sleep(3000);
                } else {
                    break;
                }
            }

            String comment = "\"" + inputComment + "\"";

            int max = Integer.MIN_VALUE;
            for (ReviewData data : rvList) {
                if (max < data.getReviewNum()) {
                    max = data.getReviewNum();
                }
            }
            ReviewData data = new ReviewData(max + 1, movieNum, reviewRate, comment);

            rvList.add(data);
            clear();
            System.out.println("╭──────────────────────────────────────────────────────────.★..─╮");
            System.out.printf("\n     해당 리뷰가 등록처리 되었습니다!\n\n");
            System.out.printf("   영화 이름 : %s | 평점 %.1f\n  리뷰 : %s\n\n", movieName, reviewRate, comment);
            System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

            Thread.sleep(3000);
            clear();
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void lookUpReview(ArrayList<MovieData> mvList, ArrayList<ReviewData> rvList, Scanner scan) {
        try {
            clear();
            int pageSize = 5;
            int totalReviews = rvList.size();
            int totalPages = (int) Math.ceil((double) totalReviews / pageSize);

            int currentPage = 1;

            while (true) {
                clear();
                System.out.printf("╭──────────────────── 리뷰 목록 (Page %d/%d)──────────────────.★..─╮\n\n", currentPage,
                        totalPages);
                int startIdx = (currentPage - 1) * pageSize;
                int endIdx = Math.min(startIdx + pageSize, totalReviews);

                for (int i = startIdx; i < endIdx; i++) {
                    ReviewData data = rvList.get(i);
                    String findName = null;

                    for (MovieData data2 : mvList) {
                        if (data.getMovieNum() == data2.getMovieNum()) {
                            findName = data2.getMovieName();
                            break;
                        }
                    }

                    System.out.printf("     영화 제목 : %s | 평점 : %.1f\n", findName, data.getReviewRate());
                    System.out.printf("     코멘트 : %s\n\n", data.getComment());
                }
                System.out.println("╰─..★.──────────────────────────────────────────────────────────╯");

                System.out.println("\n<Remote controler>");
                System.out.printf("1. 이전 페이지\t2. 다음 페이지\t3. 페이지 검색\t4. 종료\n");

                int choice = Integer.parseInt(getValidInput(
                        "Input controler > ",
                        "^[1-4]$",
                        "1에서 4 사이의 숫자를 입력해주세요.",
                        scan));

                switch (choice) {
                    case 1:
                        if (currentPage > 1) {
                            currentPage--;
                        } else {
                            System.out.println("첫 번째 페이지입니다.");
                            Thread.sleep(1500);
                        }
                        break;
                    case 2:
                        if (currentPage < totalPages) {
                            currentPage++;
                        } else {
                            System.out.println("마지막 페이지입니다.");
                            Thread.sleep(1500);
                        }
                        break;
                    case 3:
                        int goToPage = Integer.parseInt(getValidInput(
                                "이동할 페이지 번호를 입력하세요 ( 1 ~ %d ): ",
                                "^[1-9][0-9]*$",
                                "유효한 페이지 번호를 입력해주세요.",
                                scan,
                                totalPages));

                        if (goToPage >= 1 && goToPage <= totalPages) {
                            currentPage = goToPage;
                        } else {
                            System.out.println("잘못된 페이지 번호입니다.");
                            Thread.sleep(1500);
                        }
                        break;
                    case 4:
                        clear();
                        return;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("인터럽트 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
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

    public String getValidInput(String request, String regex, String errorMessage, Scanner scan, Object... formatArgs) {
        String input = null;
        while (true) {
            System.out.printf(request, formatArgs);
            input = scan.nextLine();
            if (Pattern.matches(regex, input)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }
}
