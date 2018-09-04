package com.lindar.purify.api;

import com.lindar.purify.util.WebPurifyConfigs;
import com.lindar.purify.util.WebPurifyResponse;
import com.lindar.wellrested.WellRestedRequest;
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

    WebPurifyResponse addWord(String method,String word){
        String url = UrlAcolyte.addParams(webPurifyConfigs.getUrl(), buildParamMap(method, word));
        return sendRequest(url);
    }

    WebPurifyResponse removeWord(String method,String word){
        String url = UrlAcolyte.addParams(webPurifyConfigs.getUrl(), buildParamMap(method, word));
        return sendRequest(url);
    }

    WebPurifyResponse listWords(String method){
        String url = UrlAcolyte.addParams(webPurifyConfigs.getUrl(), buildParamMap(method));
        return sendRequest(url);
    }

    WebPurifyResponse sendRequest(String url){
        return WellRestedRequest.builder().url(url).build().get().submit().fromJson().castTo(WebPurifyResponse.class);
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


}
