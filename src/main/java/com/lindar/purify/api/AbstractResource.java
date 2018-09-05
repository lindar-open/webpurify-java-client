package com.lindar.purify.api;

import com.lindar.purify.util.Messages;
import com.lindar.purify.util.WebPurifyConfigs;
import com.lindar.purify.util.WebPurifyResponse;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;
import com.lindar.wellrested.vo.WellRestedResponse;
import lindar.acolyte.util.ListsAcolyte;
import lindar.acolyte.util.UrlAcolyte;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractResource {

    private static final String FORMAT = "json";

    private WebPurifyConfigs webPurifyConfigs;

    public AbstractResource(WebPurifyConfigs webPurifyConfigs){
        this.webPurifyConfigs = webPurifyConfigs;
    }

    <T> Result<T> addWord(String method,String word){
        String url = UrlAcolyte.addParams(webPurifyConfigs.getUrl(), buildParamMap(method, word));
        return sendRequest(url, Messages.SUCCESS.WORD_ADDED);
    }

    <T> Result<T> removeWord(String method,String word){
        String url = UrlAcolyte.addParams(webPurifyConfigs.getUrl(), buildParamMap(method, word));
        return sendRequest(url, Messages.SUCCESS.WORD_REMOVED);
    }

    <T> Result<List<T>> listWords(String method){
        String url = UrlAcolyte.addParams(webPurifyConfigs.getUrl(), buildParamMap(method));
        return sendRequest(url, Messages.SUCCESS.WORDS_FETCHED);
    }

    private <T> Result<T> sendRequest(String url,String successMessage){
        WellRestedResponse wellRestedResponse = WellRestedRequest.builder().url(url).build().get().submit();
        if(!wellRestedResponse.isValid()){
            return ResultBuilder.failed(wellRestedResponse.getServerResponse());
        }
        return convertToResult(wellRestedResponse,successMessage);
    }

    private Map<String,String> buildParamMap(String method){
        return buildParamMap(method,StringUtils.EMPTY);
    }

    private Map<String,String> buildParamMap(String method,String word){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("api_key", webPurifyConfigs.getApiKey());
        paramMap.put("method", method);
        paramMap.put("format", FORMAT);
        if (StringUtils.isNotBlank(word)) {
            paramMap.put("word", word);
        }
        return paramMap;
    }

    private <T> Result<T> convertToResult(WellRestedResponse wellRestedResponse,String successMessage){
        WebPurifyResponse webPurifyResponse = wellRestedResponse.fromJson().castTo(WebPurifyResponse.class);
        if(webPurifyResponse == null){
            return ResultBuilder.failed(wellRestedResponse.getServerResponse());
        }
        if(webPurifyResponse.isSuccess() && ListsAcolyte.isNotEmpty(webPurifyResponse.listWords())){
            return (Result<T>) ResultBuilder.successful(webPurifyResponse.listWords(), successMessage);
        }else if(ListsAcolyte.isEmpty(webPurifyResponse.listWords())){
            return (Result<T>) ResultBuilder.successful(webPurifyResponse.listWords(), successMessage);
        }
        String message;
        switch (wellRestedResponse.getStatusCode()){
            case 100: message = Messages.ERROR.API_KEY_INVALID;
            break;

            case 101: message =  Messages.ERROR.API_KEY_INACTIVE;
            break;

            case 102: message = Messages.ERROR.API_KEY_MISSING;
            break;

            case 103: message =  Messages.ERROR.SERVICE_UNAVAILABLE;
            break;

            case 105: message = Messages.ERROR.WORD_EMPTY;
            break;

            case 503: message = Messages.ERROR.EXCEEDED_CALL_LIMIT;
            break;

            default: message = Messages.ERROR.UNKNOWN;
            break;
        }

        return ResultBuilder.failed(message);
    }


}
