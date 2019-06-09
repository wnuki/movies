package com.movies.client;

import com.movies.config.MovieConfig;
import com.movies.domain.MovieDto;
import com.movies.domain.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MovieClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieClient.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MovieConfig movieConfig;

    public ResponseDto getMovies() {
        URI uri = UriComponentsBuilder.fromHttpUrl(movieConfig.getMdbApiEndpoint() + "/movie")
                .queryParam("api_key", movieConfig.getMdbApiKey())
                .queryParam("sort_by", "popularity.desc")
                .queryParam("primary_release_year", 2000)
                .queryParam("vote_average.gte", 8)
                .queryParam("page", 1).build().encode().toUri();
        LOGGER.info("Url created " + uri);
        try {
            ResponseDto response = restTemplate.getForObject(uri, ResponseDto.class);
            return Optional.ofNullable(response).orElse(new ResponseDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}
