package com.omdb.api.tests.dto;

public enum TypeFilm {

    MOVIE("movie"),
    SERIES("series"),
    EPISODE("episode");

    private String type;

    TypeFilm(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
