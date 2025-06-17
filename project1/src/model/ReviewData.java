package model;

public class ReviewData {
    private int reviewNum;
    private int movieNum;
    private double reviewRate;
    private String reviewComment;

    public ReviewData() {
        this(0, 0, 0.0, null);
    }

    public ReviewData(int reviewNum, int movieNum, double reviewRate, String reviewComment) {
        this.reviewNum = reviewNum;
        this.movieNum = movieNum;
        this.reviewRate = reviewRate;
        this.reviewComment = reviewComment;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }

    public double getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(double reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getreviewComment() {
        return reviewComment;
    }

    public void setreviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    @Override
    public String toString() {
        return "reviewData [reviewNum=" + reviewNum + ", movieNum=" + movieNum + ", reviewRate=" + reviewRate
                + ", reviewComment=" + reviewComment + "]";
    }

}
