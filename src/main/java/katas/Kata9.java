package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.InterestingMoment;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> filter = movieLists.stream()
                .map(MovieList::getVideos)
                .flatMap(Collection::stream)
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "interestingMoments", movie.getInterestingMoments()
                                .stream()
                                .map(InterestingMoment::getTime)
                                .collect(Collectors.toUnmodifiableList()),
                        "url", movie.getBoxarts()
                                .stream()
                                .reduce(((boxArts, boxArts2) -> boxArts.getWidth() < boxArts2.getWidth() && boxArts.getHeight() < boxArts2.getHeight() ? boxArts: boxArts2))
                                .get()
                                .getUrl()
                ))
                .collect(Collectors.toUnmodifiableList());

        System.out.println(filter);

        //return ImmutableList.of(ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl"));
        return filter;
    }
}
