package com.movies.controller;

import com.movies.client.MovieClient;
import com.movies.domain.Movie;
import com.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
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
    public List<Movie> getResponse(@RequestParam int releaseYear, String language, double vote_average) {
        String languageCode = getISO2Code(language);
        return movieClient.getResult(releaseYear, languageCode, vote_average).getMovies().stream()
                .map(m -> movieMapper.mapToMovie(m))
                .collect(Collectors.toList());
    }
    private String getISO2Code(String language) {
        String langCode = null;
        for (Locale locale : Locale.getAvailableLocales()) {
            if (language.toLowerCase().equals(locale.getDisplayLanguage().toLowerCase())) {
                langCode = locale.getLanguage().toLowerCase();
                return langCode;
            }
        }
        throw new IllegalArgumentException("No language found: " + langCode);
    }
}
