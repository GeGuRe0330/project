package dao;

import java.util.ArrayList;
import model.ReservationData;

public interface ReservationDAO {
    
    // 전체 예약 목록 불러오기
    ArrayList<ReservationData> getAllReservations();

    // 특정 전화번호로 예약 정보 검색
    ReservationData getReservationByPhone(String phoneNum);

    // 새 예약 추가
    boolean insertReservation(ReservationData reservation);

    // 전화번호 기준 예약 삭제
    boolean deleteReservation(String phoneNum);

    // 좌석 번호나 영화 이름 등으로 수정이 필요한 경우
    boolean updateReservation(ReservationData reservation, String selectNum);
}
