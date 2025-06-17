package dao;

import java.util.ArrayList;
import model.ReviewData;

public interface ReviewDAO {
    // 리뷰데이터 ArrayList로 가져오기
    ArrayList<ReviewData> getAllReviews();

    // 리뷰데이터 검색(영화넘버)
    ArrayList<ReviewData> getReviewsByMovieNum(int movieNum);
    boolean insertReview(ReviewData review);
    boolean updateReview(ReviewData review);
    boolean deleteReview(int reviewNum);
}
