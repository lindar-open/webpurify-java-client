package com.lindar.webpurify.util.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class WebPurifyInnerResponse {

    private int success;

    @SerializedName(value = "word")
    private List<String> words;

    @SerializedName(value = "found")
    private Integer wordsFound;

    private String text;

    @SerializedName(value = "expletive")
    public List<String> profanityList;

}