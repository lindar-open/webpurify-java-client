package com.lindar.webpurify.util.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum RequestType {

    CHECK("Check"),
    COUNT("Count"),
    REPLACE("Replace"),
    RETURN("Return");

    @Getter
    private final String label;

    RequestType(String label){
        this.label = label;
    }

    public static List<RequestType> listAll(){
        return Arrays.asList(values());
    }

}
