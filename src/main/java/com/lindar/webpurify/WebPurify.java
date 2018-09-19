package com.lindar.webpurify;

import com.lindar.webpurify.api.CheckResource;
import com.lindar.webpurify.api.CountResource;
import com.lindar.webpurify.api.ReplaceResource;
import com.lindar.webpurify.api.ReturnResource;
import com.lindar.webpurify.api.admin.BlackListResource;
import com.lindar.webpurify.api.admin.WhiteListResource;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;

public class WebPurify {

    private BlackListResource blackListResource;
    private WhiteListResource whiteListResource;

    private CheckResource   checkResource;
    private CountResource   countResource;
    private ReplaceResource replaceResource;
    private ReturnResource  returnResource;

    public WebPurify(WebPurifyConfigs webPurifyConfigs) {
        this.blackListResource = new BlackListResource(webPurifyConfigs);
        this.whiteListResource = new WhiteListResource(webPurifyConfigs);

        this.checkResource = new CheckResource(webPurifyConfigs);
        this.countResource = new CountResource(webPurifyConfigs);
        this.replaceResource = new ReplaceResource(webPurifyConfigs);
        this.returnResource = new ReturnResource(webPurifyConfigs);
    }

    public BlackListResource blackList() {
        return this.blackListResource;
    }

    public WhiteListResource whiteList() {
        return this.whiteListResource;
    }

    public CheckResource check() {
        return this.checkResource;
    }

    public CountResource count() {
        return this.countResource;
    }

    public ReplaceResource replace() {
        return this.replaceResource;
    }

    public ReturnResource returns() {
        return this.returnResource;
    }

}
