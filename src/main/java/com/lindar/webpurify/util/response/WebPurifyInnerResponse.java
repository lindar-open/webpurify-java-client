package com.lindar.webpurify.util.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class WebPurifyInnerResponse {

    private int success;

    private List<String> words;

    private Integer wordsFound;

    private String text;

    public List<String> profanityList;

}