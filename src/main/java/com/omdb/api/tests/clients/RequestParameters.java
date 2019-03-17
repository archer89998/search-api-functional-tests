package com.omdb.api.tests.clients;

import lombok.Data;

@Data
public class RequestParameters {
    private String title;
    private String imdbID;
    private String type;
    private String year;
    private String plot;
    private String returnFormat;
    private String callback;
    private String apiVersion;

    public RequestParameters withTitle(String title){
        this.title = title;
        return this;
    }

    public RequestParameters withImdbID(String imdbID){
        this.imdbID = imdbID;
        return this;
    }

    public RequestParameters withType(String type){
        this.type = type;
        return this;
    }

    public RequestParameters withYear(String year){
        this.year = year;
        return this;
    }

    public RequestParameters withPlot(String plot){
        this.plot = plot;
        return this;
    }

    public RequestParameters withReturnFormat(String returnFormat){
        this.returnFormat = returnFormat;
        return this;
    }

    public RequestParameters withCallback(String callback){
        this.callback = callback;
        return this;
    }

    public RequestParameters withApiVersion(String apiVersion){
        this.apiVersion = apiVersion;
        return this;
    }
}
