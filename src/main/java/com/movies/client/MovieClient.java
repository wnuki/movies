package com.movies.client;

import com.movies.config.MovieConfig;
import com.movies.domain.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
public class MovieClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieConfig movieConfig;

    public ResponseDto getResult(final int releaseYear, final String languageCode, final double vote_average) {
        URI uri = UriComponentsBuilder.fromHttpUrl(movieConfig.getMdbApiEndpoint() + "/movie")
                .queryParam("api_key", movieConfig.getMdbApiKey())
                .queryParam("sort_by", "vote_average.desc")
                .queryParam("primary_release_year", releaseYear)
                .queryParam("vote_average.gte", vote_average)
                .queryParam("vote_count.gte", 10)
                .queryParam("with_original_language", languageCode).build().encode().toUri();
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
