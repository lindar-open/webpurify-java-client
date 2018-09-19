package com.lindar.webpurify.api;


import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.Methods;
import com.lindar.webpurify.util.Params;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.webpurify.util.enums.LanguageEnum;
import com.lindar.webpurify.util.enums.RequestTypeEmum;
import com.lindar.wellrested.vo.Result;

import java.util.Map;

public class CountResource extends AbstractResource {

    public CountResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public CountRequest text(String text) {
        return new CountRequest(text);
    }

    public class CountRequest {

        private Map<String, String> formParams;

        CountRequest(String text) {
            formParams = buildFormParams(Methods.COUNT, text);
        }

        public CountRequest language(LanguageEnum language) {
            this.formParams.put(Params.LANGUAGE, language.getCode());
            return this;
        }

        public CountRequest filterEmails() {
            this.formParams.put(Params.EMAIL, "1");
            return this;
        }

        public CountRequest filterPhoneNumbers() {
            this.formParams.put(Params.PHONE, "1");
            return this;
        }

        public CountRequest filterLinks() {
            this.formParams.put(Params.LINKS, "1");
            return this;
        }

        public Result<Integer> submit() {
            return sendRequest(RequestTypeEmum.COUNT, formParams, Messages.SUCCESS.TEXT_COUNTED);
        }

    }

}
