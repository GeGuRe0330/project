package dao;

import java.sql.*;
import java.util.ArrayList;
import model.ReservationData;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public ArrayList<ReservationData> getAllReservations() {
        ArrayList<ReservationData> rsList = new ArrayList<>();
        String sql = "SELECT * FROM RESERVATION_INFO_TABLE ORDER BY MOVIE_NAME";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String phone = rs.getString("PHONE_NUM");
                String name = rs.getString("USER_NAME");
                String movie = rs.getString("MOVIE_NAME");
                int seat = rs.getInt("SEAT_NUM");

                rsList.add(new ReservationData(phone, name, movie, seat));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt, rs);
        }

        return rsList;
    }

    @Override
    public ReservationData getReservationByPhone(String phoneNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReservationData reservation = null;
        String sql = "SELECT * FROM RESERVATION_INFO_TABLE WHERE PHONE_NUM = ?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNum);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("USER_NAME");
                String movieName = rs.getString("MOVIE_NAME");
                int seatNum = rs.getInt("SEAT_NUM");

                reservation = new ReservationData(phoneNum, userName, movieName, seatNum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt, rs);
        }

        return reservation;
    }

    @Override
    public boolean insertReservation(ReservationData data) {
        String sql = "INSERT INTO RESERVATION_INFO_TABLE (PHONE_NUM, USER_NAME, MOVIE_NAME, SEAT_NUM) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data.getPhoneNum());
            pstmt.setString(2, data.getUserName());
            pstmt.setString(3, data.getMovieName());
            pstmt.setInt(4, data.getSeatNum());

            int result = pstmt.executeUpdate();
            success = result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return success;
    }

    @Override
    public boolean deleteReservation(String phoneNum) {
        String sql = "DELETE FROM RESERVATION_INFO_TABLE WHERE PHONE_NUM = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNum);

            int result = pstmt.executeUpdate();
            success = result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return success;
    }

    @Override
    public boolean updateReservation(ReservationData reservation, String selectNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE RESERVATION_INFO_TABLE SET PHONE_NUM = ?, MOVIE_NAME = ?, SEAT_NUM = ? WHERE PHONE_NUM = ?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reservation.getPhoneNum());
            pstmt.setString(2, reservation.getMovieName());
            pstmt.setInt(3, reservation.getSeatNum());
            pstmt.setString(4, selectNum);

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }
        return false;
    }
}
