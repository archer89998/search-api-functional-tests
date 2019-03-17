package com.omdb.api.tests.config;

import com.omdb.api.tests.clients.OmdbClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("common.properties")
public class MainConfig {

    @Value("${base.url}")
    private String baseUrl;

    @Value("${key}")
    private String key;

    @Bean
    public static PropertySourcesPlaceholderConfigurer getProperySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public OmdbClient getomdbClient() {
        return new OmdbClient(new RestTemplate(), baseUrl, key);
    }

}
