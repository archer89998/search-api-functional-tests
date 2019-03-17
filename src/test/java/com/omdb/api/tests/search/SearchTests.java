package com.omdb.api.tests.search;


import com.omdb.api.tests.clients.OmdbClient;
import com.omdb.api.tests.clients.RequestParameters;
import com.omdb.api.tests.config.MainConfig;
import com.omdb.api.tests.dto.DataFormat;
import com.omdb.api.tests.dto.FilmDto;
import com.omdb.api.tests.dto.TypeFilm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestClientException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigInteger;

@ContextConfiguration(classes = MainConfig.class, loader = AnnotationConfigContextLoader.class)
public class SearchTests extends AbstractTestNGSpringContextTests {

    private final static String TITLE = "Guardians of the Galaxy Vol. 2";
    private final static String IMBD_ID = "tt3896198";
    private final static String ERROR_MESSAGE_TITLE = "Movie not found!";
    private final static String ERROR_MESSAGE_IMBD_ID = "Incorrect IMDb ID.";
    private final static String RESPONSE_FALSE = "False";
    private final static String ERROR_MESSAGE_IMBD_ID_STRING = "Error converting data type varchar to int.";
    private final static String ERROR_MESSAGE_EMPTY_ID_AND_TITLE = "Something went wrong.";
    private final static String YEAR = "2017";

    @Autowired
    private OmdbClient omdbClient;

    @Test()
    public void checkEmptySearch(){
        RequestParameters parameters = new RequestParameters().withYear(YEAR);
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertEquals(filmDto.getResponse(), RESPONSE_FALSE,"Response is equals.");
        Assert.assertEquals(filmDto.getError(), ERROR_MESSAGE_EMPTY_ID_AND_TITLE,"Error message is equals.");
    }

    @Test()
    public void checkSearchByTile(){
        RequestParameters parameters = new RequestParameters().withTitle(TITLE);
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertEquals(filmDto.getImdbID(), IMBD_ID,"Imbd ID is equals.");
    }

    @Test()
    public void checkSearchByImbdId(){
        RequestParameters parameters = new RequestParameters().withImdbID(IMBD_ID);
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertEquals(filmDto.getTitle(), TITLE,"Title is equals.");
    }

    @Test()
    public void checkSearchByIncorrectTitle(){
        RequestParameters parameters = new RequestParameters().withTitle(RandomStringUtils.randomAlphabetic(10));
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertEquals(filmDto.getResponse(), RESPONSE_FALSE,"Response is equals.");
        Assert.assertEquals(filmDto.getError(), ERROR_MESSAGE_TITLE,"Error message is equals.");
    }

    @Test()
    public void checkSearchByIncorrectImbdId(){
        RequestParameters parameters = new RequestParameters().withImdbID(RandomStringUtils.random(10, true, true));
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertEquals(filmDto.getResponse(), RESPONSE_FALSE,"Response is equals.");
        Assert.assertEquals(filmDto.getError(), ERROR_MESSAGE_IMBD_ID,"Error message is equals.");
    }

    @Test()
    public void checkSearchByIncorrectImbdIdString(){
        RequestParameters parameters = new RequestParameters().withImdbID(RandomStringUtils.random(9, true, false));
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertEquals(filmDto.getResponse(), RESPONSE_FALSE,"Response is equals.");
        Assert.assertEquals(filmDto.getError(), ERROR_MESSAGE_IMBD_ID_STRING,"Error message is equals.");
    }

    @Test
    public void checkSearchByTitleTypeAndYears(){
        RequestParameters parameters = new RequestParameters().withTitle("Guardians").withYear(YEAR).withType(TypeFilm.SERIES.getType());
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
        Assert.assertTrue(filmDto.getYear().contains(YEAR),"Year is equals.");
        Assert.assertEquals(filmDto.getType(), TypeFilm.SERIES.getType(),"Type is equals.");
    }

    @Test(expectedExceptions = RestClientException.class)
    public void checkSearchByImbdIdReturnedXml(){
        RequestParameters parameters = new RequestParameters().withImdbID(IMBD_ID).withReturnFormat(DataFormat.XML.getDataFormat());
        FilmDto filmDto = omdbClient.getFilmInfo(parameters).getBody();
    }

    public Long findSmallestDividend(Long maxDivisor){
        BigInteger res = BigInteger.ONE;
        for(int i = 1; i <= maxDivisor; i++) {
            BigInteger x = BigInteger.valueOf(i);
            res = res.multiply(x).divide(res.gcd(x));
        }
        return res.longValue();
    }
}
