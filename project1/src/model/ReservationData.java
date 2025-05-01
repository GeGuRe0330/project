package model;
public class ReservationData {
    private String phoneNum;
    private String userName;
    private String movieName;
    private int seatNum;

    
    public ReservationData() {
        this(null,null,null,0);
    }

    public ReservationData(String phoneNum, String userName, String movieName, int seatNum) {
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.movieName = movieName;
        this.seatNum = seatNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public int getSeatNum() {
        return seatNum;
    }
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    @Override
    public String toString() {
        return "reservationData [phoneNum=" + phoneNum + ", userName=" + userName + ", movieName=" + movieName
                + ", seatNum=" + seatNum + "]";
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
        ReservationData other = (ReservationData) obj;
        if (movieName == null) {
            if (other.movieName != null)
                return false;
        } else if (!movieName.equals(other.movieName))
            return false;
        return true;
    }
}
