package com.movies.controller;

import com.movies.client.MovieClient;
import com.movies.domain.Movie;
import com.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/movieDb")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private MovieMapper movieMapper;

    @GetMapping(path = "/movies")
    public List<Movie> getResponse(@RequestParam int releaseYear) {
        return movieClient.getResult(releaseYear).getMovies().stream()
                .map(m -> movieMapper.mapToMovie(m))
                .collect(Collectors.toList());
    }
}
