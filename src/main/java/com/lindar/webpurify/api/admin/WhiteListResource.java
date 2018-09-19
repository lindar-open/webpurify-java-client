package com.lindar.webpurify.api.admin;


import com.lindar.webpurify.util.Methods;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.wellrested.vo.Result;

import java.util.List;

public class WhiteListResource extends AbstractAdminResource {

    public WhiteListResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public Result<Void> addWord(String word) {
        return this.addWord(Methods.WHITELIST.ADD, word);
    }

    public Result<Void> removeWord(String word) {
        return this.removeWord(Methods.WHITELIST.REMOVE, word);
    }

    public Result<List<String>> listWords() {
        return this.listWords(Methods.WHITELIST.GET);
    }

}
