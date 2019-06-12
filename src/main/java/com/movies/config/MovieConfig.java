package com.movies.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MovieConfig {
    @Value("${mdb.api.endpoint}")
    private String mdbApiEndpoint;

    @Value("${mdb.api.key}")
    private String mdbApiKey;

}
