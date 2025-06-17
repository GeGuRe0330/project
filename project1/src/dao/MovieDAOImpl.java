package dao;

import java.sql.*;
import java.util.ArrayList;

import model.MovieData;

public class MovieDAOImpl implements MovieDAO {

    @Override
    public ArrayList<MovieData> movieTableLoad() {
        ArrayList<MovieData> mvList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM MOVIE_TABLE ORDER BY MOVIE_NUM";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int movieNum = rs.getInt("MOVIE_NUM");
                String movieName = rs.getString("MOVIE_NAME");
                String releaseDate = rs.getDate("RELEASE_DATE").toString();
                int reservationCount = rs.getInt("RESERVATION_COUNT");

                mvList.add(new MovieData(movieNum, movieName, releaseDate, reservationCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt, rs);
        }

        return mvList;
    }

    @Override
    public MovieData getMovieByName(String name) {
        MovieData movie = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM MOVIE_TABLE WHERE MOVIE_NAME = ?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int movieNum = rs.getInt("MOVIE_NUM");
                String movieName = rs.getString("MOVIE_NAME");
                String releaseDate = rs.getDate("RELEASE_DATE").toString();
                int reservationCount = rs.getInt("RESERVATION_COUNT");

                movie = new MovieData(movieNum, movieName, releaseDate, reservationCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt, rs);
        }

        return movie;
    }

    @Override
    public boolean updateMovie(MovieData movie) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE MOVIE_TABLE SET MOVIE_NAME = ?, RELEASE_DATE = TO_DATE(?, 'YYYY-MM-DD'), RESERVATION_COUNT = ? WHERE MOVIE_NUM = ?";
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getReleaseDate());
            pstmt.setInt(3, movie.getReservationCount());
            pstmt.setInt(4, movie.getMovieNum());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return result > 0;
    }

    @Override
    public boolean insertMovie(MovieData movie) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO MOVIE_TABLE (MOVIE_NAME, RELEASE_DATE, RESERVATION_COUNT) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getReleaseDate());
            pstmt.setInt(3, movie.getReservationCount());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return result > 0;
    }

    @Override
    public boolean deleteMovie(int movieNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM MOVIE_TABLE WHERE MOVIE_NUM = ?";
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieNum);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return result > 0;
    }
    
}
