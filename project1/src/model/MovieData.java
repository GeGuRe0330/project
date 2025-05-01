package model;
public class MovieData {
    private int movieNum;
    private String movieName;
    private String releaseDate;
    private int reservationCount;

    
    public MovieData() {
        this(0,null,null,0);
    }

    public MovieData(int movieNum, String movieName, String releaseDate, int reservationCount) {
        this.movieNum = movieNum;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.reservationCount = reservationCount;
    }


    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    @Override
    public String toString() {
        return "MovieData [movieNum=" + movieNum + ", movieName=" + movieName + ", releaseDate=" + releaseDate
                + ", reservationCount=" + reservationCount + "]";

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MovieData other = (MovieData) obj;
        if (movieName == null) {
            if (other.movieName != null)
                return false;
        } else if (!movieName.equals(other.movieName))
            return false;
        return true;
    }

    

}
