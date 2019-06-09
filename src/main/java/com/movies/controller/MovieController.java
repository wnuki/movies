package com.movies.controller;

import com.movies.client.MovieClient;
import com.movies.domain.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/movieDb")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieClient movieClient;

    @GetMapping(path = "movies")
    public ResponseDto getMovies() {
        return movieClient.getMovies();
    }

}
