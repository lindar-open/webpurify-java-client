package com.lindar.purify.api;

import com.lindar.purify.util.Methods;
import com.lindar.purify.util.WebPurifyConfigs;
import com.lindar.purify.util.WebPurifyResponse;

public class BlackListResource extends AbstractResource {

    public BlackListResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public WebPurifyResponse addWord(String word){
        return this.addWord(Methods.BLACKLIST.ADD , word);
    }

    public WebPurifyResponse removeWord(String word){
        return this.removeWord(Methods.BLACKLIST.REMOVE , word);
    }

    public WebPurifyResponse listWords(){
        return this.listWords(Methods.BLACKLIST.GET);
    }

}
