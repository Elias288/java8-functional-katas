package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> filter = movieLists.stream()
                .map(MovieList::getVideos)
                .flatMap(Collection::stream)
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "boxart", movie.getBoxarts().stream()
                                .reduce(((boxArts, boxArts2) -> boxArts.getWidth() < boxArts2.getWidth() && boxArts.getHeight() < boxArts2.getHeight() ? boxArts: boxArts2))
                                .get()
                                .getUrl()
                ))
                .collect(Collectors.toList());

        return filter;
    }
}
