package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        /*movieLists.stream()
                .map(MovieList::getVideos)
                .flatMap(Collection::stream)
                .filter(movie -> movie.getBoxarts().stream().anyMatch(b -> b.getWidth() == 150 && b.getHeight() == 200))
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "boxart", movie.getBoxarts().stream()
                                        .filter(b -> b.getWidth() == 150 && b.getHeight() == 200)
                                        .findFirst()
                                        .map(boxArt -> boxArt.getWidth() + ", " + boxArt.getHeight() + ", " + boxArt.getUrl())
                ))
                .collect(Collectors.toList())
                .forEach(System.out::println);*/

        return ImmutableList.copyOf(
                movieLists.stream()
                        .map(MovieList::getVideos)
                        .flatMap(Collection::stream)
                        .filter(movie -> movie.getBoxarts().stream().anyMatch(b -> b.getWidth() == 150 && b.getHeight() == 200))
                        .map(movie -> ImmutableMap.of(
                                "id", movie.getId(),
                                "title", movie.getTitle(),
                                "boxart", movie.getBoxarts().stream()
                                        .filter(b -> b.getWidth() == 150 && b.getHeight() == 200)
                                        .findFirst()
                                        .map(boxArt -> boxArt.getWidth() + ", " + boxArt.getHeight() + ", " + boxArt.getUrl())
                        ))
                        .collect(Collectors.toList())
        );
    }
}
