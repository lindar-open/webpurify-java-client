package com.lindar.webpurify.api.admin;


import com.lindar.webpurify.api.AbstractResource;
import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.wellrested.vo.Result;

abstract class AbstractAdminResource extends AbstractResource {

    AbstractAdminResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    <T> Result<T> addWord(String method, String word){
        return sendRequest(buildFormParamsMap(method, word) , Messages.SUCCESS.WORD_ADDED);
    }

    <T> Result<T> removeWord(String method,String word){
        return sendRequest(buildFormParamsMap(method, word) , Messages.SUCCESS.WORD_REMOVED);
    }

    <T> Result<T> listWords(String method){
        return sendRequest(buildFormParamsMap(method) , Messages.SUCCESS.WORDS_FETCHED);
    }

}
