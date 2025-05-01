package service;

import java.util.ArrayList;
import java.util.Scanner;

import model.MovieData;
import model.ReservationData;
import model.ReviewData;

public interface MovieInterface {
    // 데이터 로드

    // Load movieTable : movieTavle 파일 로드
    void movieTableLoad(ArrayList<MovieData> mvList);

    // Load reservationInfoTable : reservationInfoTable 파일 로드
    void resTableLoad(ArrayList<ReservationData> rsList);

    // Load reviewTable : reviewTable 파일 로드
    void reviewTableLoad(ArrayList<ReviewData> rvList);

    // 데이터 저장

    // Save movieTable : movieTavle 파일 저장
    void movieTableSave(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList);

    // Save reservationInfoTable : reservationInfoTable 파일 저장
    void resTableSave(ArrayList<ReservationData> rsList);

    // Save reviewTable : reviewTable 파일 저장
    void reviewTableSave(ArrayList<ReviewData> rvList);

    // 관리자 메소드

    // Registration movie : 영화 등록
    void movieRegistration(ArrayList<MovieData> mvList, Scanner scan);

    // Modify movie : 등록 영화 수정
    void movieModify(ArrayList<MovieData> mvList, Scanner scan);

    // Delete movie : 등록 영화 삭제
    void movieDelete(ArrayList<MovieData> mvList, Scanner scan);

    // Check min | max reservation : 영화 예매 최대값 | 최소값
    void checkMovieRank(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan);

    // 사용자 메소드
    // 영화 순위

    // Check movielist : 개봉 영화 리스트 확인
    void checkMovieList(ArrayList<MovieData> mvList, Scanner scan);

    // Sort movielist (to reservation) : 예매수 기준 오름림차 정렬
    void sortMovieList(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan);

    // descending order (to reservation) : 예매수 기준 내림차 정렬
    void dessortMovieList(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan);

    // 영화 예매

    // Reservation movie : 영화 예매
    void movieReservation(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan);

    // Cancel reservation : 예매 취소
    void cancelReservation(ArrayList<ReservationData> rsList, Scanner scan);

    // Check reservation : 예매 확인
    void checkReservation(ArrayList<ReservationData> rsList, Scanner scan);

    // Edit reservation : 예매 수정
    void editReservation(ArrayList<MovieData> mvList, ArrayList<ReservationData> rsList, Scanner scan);

    // 영화 리뷰

    // write a review : 리뷰 작성
    void writeReview(ArrayList<MovieData> mvList, ArrayList<ReviewData> rvList, Scanner scan);

    // look up review : 리뷰 열람
    void lookUpReview(ArrayList<MovieData> mvList, ArrayList<ReviewData> rvList, Scanner scan); //

    // 시스템 함수

    // clear : 터미널 클리어
    void clear();

    // 패턴검색
    String getValidInput(String request, String regex, String errorMessage, Scanner scan, Object... formatArgs);
}
