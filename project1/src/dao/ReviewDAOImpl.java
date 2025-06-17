package dao;

import java.sql.*;
import java.util.ArrayList;

import model.ReviewData;

public class ReviewDAOImpl implements ReviewDAO {

    @Override
    public ArrayList<ReviewData> getAllReviews() {
        ArrayList<ReviewData> reviewList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM REVIEW_TABLE ORDER BY REVIEW_NUM";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int reviewNum = rs.getInt("REVIEW_NUM");
                int movieNum = rs.getInt("MOVIE_NUM");
                double reviewRate = rs.getDouble("REVIEW_RATE");
                String reviewComment = rs.getString("REVIEW_COMMENT");

                reviewList.add(new ReviewData(reviewNum, movieNum, reviewRate, reviewComment));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt, rs);
        }

        return reviewList;
    }

    @Override
    public ArrayList<ReviewData> getReviewsByMovieNum(int movieNum) {
        ArrayList<ReviewData> reviewList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM REVIEW_TABLE WHERE MOVIE_NUM = ? ORDER BY REVIEW_NUM";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieNum);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int reviewNum = rs.getInt("REVIEW_NUM");
                double reviewRate = rs.getDouble("REVIEW_RATE");
                String reviewComment = rs.getString("REVIEW_COMMENT");

                reviewList.add(new ReviewData(reviewNum, movieNum, reviewRate, reviewComment));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt, rs);
        }

        return reviewList;
    }

    @Override
    public boolean insertReview(ReviewData review) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO REVIEW_TABLE (MOVIE_NUM, REVIEW_RATE, REVIEW_COMMENT) VALUES (?, ?, ?)";
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, review.getMovieNum());
            pstmt.setDouble(2, review.getReviewRate());
            pstmt.setString(3, review.getreviewComment());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return result > 0;
    }

    @Override
    public boolean updateReview(ReviewData review) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE REVIEW_TABLE SET REVIEW_RATE = ?, REVIEW_COMMENT = ? WHERE REVIEW_NUM = ?";
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, review.getReviewRate());
            pstmt.setString(2, review.getreviewComment());
            pstmt.setInt(3, review.getReviewNum());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return result > 0;
    }

    @Override
    public boolean deleteReview(int reviewNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM REVIEW_TABLE WHERE REVIEW_NUM = ?";
        int result = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reviewNum);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(conn, pstmt);
        }

        return result > 0;
    }
}
