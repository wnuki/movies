package com.movies.mapper;

import com.movies.domain.Movie;
import com.movies.domain.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie mapToMovie(MovieDto movieDto) {
        return new Movie(
                movieDto.getTitle(),
                movieDto.getRelease_date(),
                movieDto.getVote_average()
        );
    }
}
