package com.lindar.webpurify.api;


import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.Methods;
import com.lindar.webpurify.util.Params;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.webpurify.util.enums.LanguageEnum;
import com.lindar.webpurify.util.enums.RequestTypeEmum;
import com.lindar.wellrested.vo.Result;

import java.util.HashMap;
import java.util.Map;

public class ReplaceResource extends AbstractResource {

    public ReplaceResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public ReplaceRequest replace(String text,String replacement){
        return new ReplaceRequest(text,replacement);
    }

    public class ReplaceRequest {

        private Map<String,String> formParams;

        ReplaceRequest(String text,String replacement){
            formParams = new HashMap<>();
            formParams.put(Params.API_KEY, webPurifyConfigs.getApiKey());
            formParams.put(Params.METHOD, Methods.REPLACE);
            formParams.put(Params.FORMAT, FORMAT);
            formParams.put(Params.TEXT, text);
            formParams.put(Params.REPLACE_SYMBOL, replacement
            );
        }

        public ReplaceRequest language(LanguageEnum language){
            this.formParams.put(Params.LANGUAGE, language.getCode());
            return this;
        }

        public ReplaceRequest filterEmails(){
            this.formParams.put(Params.EMAIL, "1");
            return this;
        }

        public ReplaceRequest filterPhoneNumbers(){
            this.formParams.put(Params.PHONE, "1");
            return this;
        }

        public ReplaceRequest filterLinks(){
            this.formParams.put(Params.LINKS, "1");
            return this;
        }

        public Result<String> submit(){
            return sendRequest(RequestTypeEmum.REPLACE,formParams, Messages.SUCCESS.TEXT_REPLACED);
        }

    }

}
