package com.lindar.webpurify.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.webpurify.util.enums.RequestTypeEmum;
import com.lindar.webpurify.util.response.WebPurifyInnerResponse;
import com.lindar.webpurify.util.response.WebPurifyResponse;
import com.lindar.webpurify.util.response.WebPurifyResponseDeserializer;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;
import com.lindar.wellrested.vo.WellRestedResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResource {

    protected static final String FORMAT = "json";

    protected WebPurifyConfigs webPurifyConfigs;

    protected AbstractResource(WebPurifyConfigs webPurifyConfigs) {
        this.webPurifyConfigs = webPurifyConfigs;
    }

    protected <T> Result<T> sendRequest(Map<String, String> formParams, String successMessage) {
        return sendRequest(null, formParams, successMessage);
    }

    protected <T> Result<T> sendRequest(RequestTypeEmum requestTypeEmum, Map<String, String> formParams, String successMessage) {
        WellRestedResponse wellRestedResponse = WellRestedRequest.builder()
                                                                 .url(this.webPurifyConfigs.getUrl())
                                                                 .build().post()
                                                                 .formParams(formParams)
                                                                 .submit();
        if (!wellRestedResponse.isValid()) {
            return ResultBuilder.failed(wellRestedResponse.getServerResponse());
        }
        return convertToResult(requestTypeEmum, wellRestedResponse, successMessage);
    }

    protected Map<String, String> buildFormParamsMap(String method) {
        return buildFormParamsMap(method, StringUtils.EMPTY);
    }

    protected Map<String, String> buildFormParamsMap(String method, String word) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("api_key", webPurifyConfigs.getApiKey());
        paramMap.put("method", method);
        paramMap.put("format", FORMAT);
        if (StringUtils.isNotBlank(word)) {
            paramMap.put("word", word);
        }
        return paramMap;
    }

    private <T> Result<T> convertToResult(RequestTypeEmum requestTypeEmum, WellRestedResponse wellRestedResponse, String successMessage) {
        WebPurifyResponse webPurifyResponse = castToWebPurifyResponse(wellRestedResponse);
        if (webPurifyResponse == null) {
            return ResultBuilder.failed(wellRestedResponse.getServerResponse());
        }

        if (requestTypeEmum != null && wellRestedResponse.getStatusCode() == 200) {
            switch (requestTypeEmum) {
                case CHECK:
                    return ResultBuilder.successful((T) webPurifyResponse.wordsFound(), successMessage);

                case COUNT:
                    return ResultBuilder.successful((T) webPurifyResponse.amountOfWordsFound(), successMessage);

                case REPLACE:
                    return ResultBuilder.successful((T) webPurifyResponse.getText(), successMessage);

                case RETURN:
                    return ResultBuilder.successful((T) webPurifyResponse.listProfanity(), successMessage);
            }
        }

        if (webPurifyResponse.isSuccess() && webPurifyResponse.containsWords()) {
            return ResultBuilder.successful((T) webPurifyResponse.listWords(), successMessage);
        } else if (webPurifyResponse.isSuccess()) {
            return ResultBuilder.successfulWithoutData(successMessage);
        }

        String message;
        switch (wellRestedResponse.getStatusCode()) {
            case 100:
                message = Messages.ERROR.API_KEY_INVALID;
                break;

            case 101:
                message = Messages.ERROR.API_KEY_INACTIVE;
                break;

            case 102:
                message = Messages.ERROR.API_KEY_MISSING;
                break;

            case 103:
                message = Messages.ERROR.SERVICE_UNAVAILABLE;
                break;

            case 105:
                message = Messages.ERROR.WORD_EMPTY;
                break;

            case 503:
                message = Messages.ERROR.EXCEEDED_CALL_LIMIT;
                break;

            default:
                message = Messages.ERROR.UNKNOWN;
                break;
        }

        return ResultBuilder.failed(message);
    }

    private WebPurifyResponse castToWebPurifyResponse(WellRestedResponse wellRestedResponse){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(WebPurifyInnerResponse.class, new WebPurifyResponseDeserializer())
                .create();
        return gson.fromJson(wellRestedResponse.getServerResponse(), WebPurifyResponse.class);
    }


}
