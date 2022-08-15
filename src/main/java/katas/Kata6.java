package katas;

import model.Movie;
import util.DataUtil;

import java.util.Collection;
import java.util.List;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        String filter = movies.stream()
                .map(Movie::getBoxarts)
                .flatMap(Collection::stream)
                .reduce(((boxArts, boxArts2) -> boxArts.getWidth() > boxArts2.getWidth() && boxArts.getHeight() > boxArts2.getHeight() ? boxArts: boxArts2))
                .get()
                .getUrl();

        return filter;
    }
}
