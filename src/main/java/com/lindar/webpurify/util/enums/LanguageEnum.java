package com.lindar.webpurify.util.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum LanguageEnum {

    EN("English","en");

    @Getter
    private final String label;


    @Getter
    private final String code;

    LanguageEnum(String label,String code){
        this.label = label;
        this.code = code;
    }

    public static List<LanguageEnum> listAll() {
        return Arrays.asList(values());
    }

}
