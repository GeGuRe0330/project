package comparator;

import java.util.Comparator;
import model.MovieData;

public class RsComparator implements Comparator<MovieData> {
    private boolean ascending;

    public RsComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(MovieData R1, MovieData R2) {
        if (ascending) {
            // Ture : ascending
            return Integer.compare(R1.getReservationCount(), R2.getReservationCount());
        } else {
            // false : descending
            return Integer.compare(R2.getReservationCount(), R1.getReservationCount());
        }
    }
}