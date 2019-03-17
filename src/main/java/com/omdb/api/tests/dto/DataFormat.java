package com.omdb.api.tests.dto;

public enum DataFormat {

    JSON("json"),
    XML("xml");

    private String dataFormat;

    DataFormat(String dataFormat){
        this.dataFormat = dataFormat;
    }

    public String getDataFormat(){
        return this.dataFormat;
    }
}
