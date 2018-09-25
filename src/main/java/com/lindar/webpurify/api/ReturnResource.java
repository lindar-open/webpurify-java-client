package com.lindar.webpurify.api;


import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.Methods;
import com.lindar.webpurify.util.Params;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.webpurify.util.enums.LanguageEnum;
import com.lindar.webpurify.util.enums.RequestTypeEmum;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;

import java.util.List;
import java.util.Map;

public class ReturnResource extends AbstractResource {

    public ReturnResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public ReturnRequest returns(String text, String replacement) {
        return new ReturnRequest(text, replacement);
    }

    public class ReturnRequest {

        private Map<String, String> formParams;

        ReturnRequest(String text, String replacement) {
            formParams = buildFormParams(Methods.RETURN, text, replacement);
        }

        public ReturnRequest language(LanguageEnum language) {
            this.formParams.put(Params.LANGUAGE, language.getCode());
            return this;
        }

        public ReturnRequest filterEmails() {
            this.formParams.put(Params.EMAIL, "1");
            return this;
        }

        public ReturnRequest filterPhoneNumbers() {
            this.formParams.put(Params.PHONE, "1");
            return this;
        }

        public ReturnRequest filterLinks() {
            this.formParams.put(Params.LINKS, "1");
            return this;
        }

        public <T> Result<List<String>> submit() {
            if (!isEnabled()) {
                return ResultBuilder.failed(Messages.ERROR.DISABLED);
            }
            return sendRequest(RequestTypeEmum.RETURN, formParams, Messages.SUCCESS.WORDS_FETCHED);
        }

    }

}
