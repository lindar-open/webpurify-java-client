package com.lindar.purify.api;

import com.lindar.purify.util.Methods;
import com.lindar.purify.util.WebPurifyConfigs;
import com.lindar.purify.util.WebPurifyResponse;

public class WhiteListResource extends AbstractResource {

    public WhiteListResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public WebPurifyResponse addWord(String word){
        return this.addWord(Methods.WHITELIST.ADD , word);
    }

    public WebPurifyResponse removeWord(String word){
        return this.removeWord(Methods.WHITELIST.REMOVE , word);
    }

    public WebPurifyResponse listWords(){
        return this.listWords(Methods.WHITELIST.GET);
    }

}
