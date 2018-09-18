package com.lindar.webpurify.util.response;

import com.google.gson.annotations.SerializedName;
import lindar.acolyte.util.ListsAcolyte;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class WebPurifyResponse {

    @SerializedName(value = "rsp")
    private WebPurifyInnerResponse webPurifyInnerResponse;

    public Boolean isSuccess() {
        return webPurifyInnerResponse != null;
    }

    public List<String> listWords() {
        return isSuccess() ? webPurifyInnerResponse.getWords() : new ArrayList<>();
    }

    public List<String> listProfanity() {
        return isSuccess() ? webPurifyInnerResponse.getProfanityList() : new ArrayList<>();
    }

    public Boolean containsWords() {
        return ListsAcolyte.isNotEmpty(listWords());
    }

    public Boolean containsProfanity() {
        return ListsAcolyte.isNotEmpty(listProfanity());
    }

    public Integer amountOfWordsFound() {
        return isSuccess() ? webPurifyInnerResponse.getWordsFound() : null;
    }

    public Boolean wordsFound() {
        return !NumberUtils.INTEGER_ZERO.equals(amountOfWordsFound());
    }

    public String getText() {
        return isSuccess() ? StringUtils.defaultIfBlank(webPurifyInnerResponse.getText(), null) : null;
    }

}


