package com.lindar.webpurify;

import com.lindar.webpurify.api.BlackListResource;
import com.lindar.webpurify.api.WhiteListResource;
import com.lindar.webpurify.util.WebPurifyConfigs;

public class WebPurify {

    private BlackListResource blackListResource;
    private WhiteListResource whiteListResource;

    public WebPurify(WebPurifyConfigs webPurifyConfigs) {
        this.blackListResource = new BlackListResource(webPurifyConfigs);
        this.whiteListResource = new WhiteListResource(webPurifyConfigs);
    }

    public BlackListResource blackList() {
        return this.blackListResource;
    }

    public WhiteListResource whiteList() {
        return this.whiteListResource;
    }
}
