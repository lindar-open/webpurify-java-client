package com.lindar.purify.util;

import com.google.gson.annotations.SerializedName;
import com.lindar.wellrested.vo.Result;
import lindar.acolyte.util.ListsAcolyte;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class WebPurifyResponse {

    @SerializedName(value = "rsp")
    private Response response;

    public boolean isSuccess(){
        return response != null && (response.getSuccess() == 1 || ListsAcolyte.isNotEmpty(response.getWords()));
    }

    public List<String> listWords(){
        if(response == null || response.getWords() == null){
            return new ArrayList<>();
        }
        return response.getWords();
    }

    @Data
    private class Response{

        private int success;

        @SerializedName(value = "word")
        private List<String> words;

    }

}


