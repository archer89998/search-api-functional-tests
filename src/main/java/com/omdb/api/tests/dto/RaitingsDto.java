package com.omdb.api.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RaitingsDto {
    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;
}
