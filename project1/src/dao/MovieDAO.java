package dao;

import java.util.ArrayList;

import model.MovieData;

public interface MovieDAO {
    ArrayList<MovieData> movieTableLoad(); // SELECT

    MovieData getMovieByName(String name); // SELECT by title

    boolean updateMovie(MovieData movie); // UPDATE

    boolean insertMovie(MovieData movie); // INSERT

    boolean deleteMovie(int movieNum); // DELETE
}
