package com.lindar.purify.api.admin;

import com.lindar.purify.api.AbstractResource;
import com.lindar.purify.util.Messages;
import com.lindar.purify.util.WebPurifyConfigs;
import com.lindar.wellrested.vo.Result;

import java.util.List;

abstract class AbstractAdminResource extends AbstractResource {

    private static final String FORMAT = "json";

    AbstractAdminResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    <T> Result<T> addWord(String method,String word){
        return sendRequest(buildFormParamsMap(method,word) , Messages.SUCCESS.WORD_ADDED);
    }

    <T> Result<T> removeWord(String method,String word){
        return sendRequest(buildFormParamsMap(method,word) , Messages.SUCCESS.WORD_REMOVED);
    }

    <T> Result<List<T>> listWords(String method){
        return sendRequest(buildFormParamsMap(method) , Messages.SUCCESS.WORDS_FETCHED);
    }

}
