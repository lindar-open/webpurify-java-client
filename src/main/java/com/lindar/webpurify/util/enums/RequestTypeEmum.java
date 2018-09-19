package com.lindar.webpurify.util.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum RequestTypeEmum {

    CHECK("Check"),
    COUNT("Count"),
    REPLACE("Replace"),
    RETURN("Return");

    @Getter
    private final String label;

    RequestTypeEmum(String label) {
        this.label = label;
    }

    public static List<RequestTypeEmum> listAll() {
        return Arrays.asList(values());
    }

}
