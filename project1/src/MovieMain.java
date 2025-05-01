import service.MovieInterface;
import service.MovieMaterialize;
import view.AdminMenu;
import view.MovieMenu;
import model.MovieData;
import model.ReservationData;
import model.ReviewData;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieMain {
    public static void main(String[] args) {
        ArrayList<MovieData> mvList = new ArrayList<MovieData>();
        ArrayList<ReservationData> rsList = new ArrayList<ReservationData>();
        ArrayList<ReviewData> rvList = new ArrayList<ReviewData>();
        Scanner scan = new Scanner(System.in);

        MovieInterface mm = new MovieMaterialize();

        boolean stopFlag = false;

        mm.clear();
        mm.movieTableLoad(mvList);
        mm.resTableLoad(rsList);
        mm.reviewTableLoad(rvList);
        mm.clear();

        while (!stopFlag) {
            MovieMenu.menuDisplay();

            int inputNum = Integer.parseInt(mm.getValidInput(
                    "메뉴를 입력해주세요. > ",
                    "^(10|[1-9]|9999)$",
                    "지정된 숫자로 메뉴를 입력해주세요.",
                    scan));

            if (1 <= inputNum && inputNum <= 10) {
                switch (inputNum) {
                    case MovieMenu.MOVIELIST:
                        mm.checkMovieList(mvList, scan);
                        break;
                    case MovieMenu.POPULAR:
                        mm.dessortMovieList(mvList, rsList, scan);
                        break;
                    case MovieMenu.UNPOPULAR:
                        mm.sortMovieList(mvList, rsList, scan);
                        break;
                    case MovieMenu.RESERVATION:
                        mm.movieReservation(mvList, rsList, scan);
                        break;
                    case MovieMenu.CANCEL_RESERVATION:
                        mm.cancelReservation(rsList, scan);
                        break;
                    case MovieMenu.CHECK_RESERVATION:
                        mm.checkReservation(rsList, scan);
                        break;
                    case MovieMenu.EDIT_RESERVATION:
                        mm.editReservation(mvList, rsList, scan);
                        break;
                    case MovieMenu.WRITE_REVIEW:
                        mm.writeReview(mvList, rvList, scan);
                        break;
                    case MovieMenu.LOOKUP_REVIEW:
                        mm.lookUpReview(mvList, rvList, scan);
                        break;
                    case MovieMenu.EXIT:
                        mm.clear();
                        mm.movieTableSave(mvList, rsList);
                        mm.resTableSave(rsList);
                        mm.reviewTableSave(rvList);
                        mm.clear();
                        System.out.println("정상적으로 데이터가 저장되었습니다. 이용해주셔서 감사합니다.");
                        stopFlag = true;
                        break;

                }
            }

            if (inputNum == 9999) {
                AdminMenu.adminActive();
                while (!stopFlag) {
                    AdminMenu.adminMenuDisplay();
                    int _inputNum = Integer.parseInt(mm.getValidInput(
                            "메뉴를 입력해주세요. > ",
                            "^[1-5]$",
                            "지정된 숫자로 메뉴를 입력해주세요.",
                            scan));
                    switch (_inputNum) {
                        case AdminMenu.REGISTRATION_MOVIE:
                            mm.movieRegistration(mvList, scan);
                            break;
                        case AdminMenu.MODIFY_MOVIE:
                            mm.movieModify(mvList, scan);
                            break;
                        case AdminMenu.DELETE_MOVIE:
                            mm.movieDelete(mvList, scan);
                            break;
                        case AdminMenu.CHECK_RESERVATION:
                            mm.checkMovieRank(mvList, rsList, scan);
                            break;
                        case AdminMenu.EXIT:
                            mm.clear();
                            int sureNum = Integer.parseInt(mm.getValidInput(
                                    "변경 내용을 저장 후 종료하시겠습니까? 1. 예  / 2. 아니오 \n        > ",
                                    "^[1-2]$",
                                    "지정된 숫자로 메뉴를 입력해주세요.",
                                    scan));
                            if (sureNum == 1) {
                                mm.clear();
                                mm.movieTableSave(mvList, rsList);
                                mm.resTableSave(rsList);
                                mm.reviewTableSave(rvList);
                                mm.clear();
                                System.out.println("정상적으로 데이터가 저장되었습니다.");
                                stopFlag = true;
                            } else {
                                mm.clear();
                                System.out.println("변경 내용을 저장하지 않고 종료합니다.");
                                stopFlag = true;
                            }
                            break;
                    }
                }
            }
        }
        scan.close();
    }
}
