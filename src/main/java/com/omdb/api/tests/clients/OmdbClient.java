package com.omdb.api.tests.clients;


import com.omdb.api.tests.dto.FilmDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OmdbClient {

    private static final String API_KEY = "apikey";
    private static final String TITLE = "t";
    private static final String IMBD_ID = "i";
    private static final String TYPE = "type";
    private static final String YEAR = "y";
    private static final String PLOT = "plot";
    private static final String RETURN_TYPE = "r";
    private static final String CALLBACK = "callback";
    private static final String API_VERSION = "v";
    private RestTemplate restTemplate;
    private String baseUrl = "http://www.omdbapi.com";
    private String key = "http://www.omdbapi.com";

    private static final String EQUAL_SIGN = "=";
    private static final String AMPERSAND_SIGN = "&";
    private static final String QUESTION_SIGN = "?";
    private static final String SLASH = "/";

    public OmdbClient() {
        this.restTemplate = new RestTemplate();
    }

    public OmdbClient(RestTemplate restTemplate, String baseUrl, String key) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.key = key;
    }

    public ResponseEntity<FilmDto> getFilmInfo(RequestParameters parameters){
        StringBuilder builder = new StringBuilder();
        builder.append(baseUrl).append(SLASH + QUESTION_SIGN + AMPERSAND_SIGN + API_KEY + EQUAL_SIGN + key);
        builder.append(buildParams(parameters));
        String url = builder.toString();
        return restTemplate.exchange(url, HttpMethod.GET, null, FilmDto.class);
    }

    private StringBuilder buildParams(RequestParameters parameters){
        StringBuilder builder = new StringBuilder();
        if(parameters == null){
            return builder;
        }
        String title = parameters.getTitle();
        String imdbID = parameters.getImdbID();
        String type = parameters.getType();
        String year = parameters.getYear();
        String plot = parameters.getPlot();
        String returnFormat = parameters.getReturnFormat();
        String callback = parameters.getCallback();
        String apiVersion = parameters.getApiVersion();
        if(title != null){
            builder.append(AMPERSAND_SIGN + TITLE + EQUAL_SIGN + title);
        }
        if(imdbID != null){
            builder.append(AMPERSAND_SIGN + IMBD_ID + EQUAL_SIGN + imdbID);
        }
        if(type != null){
            builder.append(AMPERSAND_SIGN + TYPE + EQUAL_SIGN + type);
        }
        if(year != null){
            builder.append(AMPERSAND_SIGN + YEAR + EQUAL_SIGN + year);
        }
        if(plot != null){
            builder.append(AMPERSAND_SIGN + PLOT + EQUAL_SIGN + plot);
        }
        if(returnFormat != null){
            builder.append(AMPERSAND_SIGN + RETURN_TYPE + EQUAL_SIGN + returnFormat);
        }
        if(callback != null){
            builder.append(AMPERSAND_SIGN + CALLBACK + EQUAL_SIGN + callback);
        }
        if(apiVersion != null){
            builder.append(AMPERSAND_SIGN + API_VERSION + EQUAL_SIGN + apiVersion);
        }

        return builder;
    }
}
