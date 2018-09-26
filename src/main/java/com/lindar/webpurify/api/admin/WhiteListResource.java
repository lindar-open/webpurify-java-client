package com.lindar.webpurify.api.admin;


import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.Methods;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;

import java.util.List;

public class WhiteListResource extends AbstractAdminResource {

    public WhiteListResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public Result<Void> addWord(String word) {
        if (!isEnabled()) {
            return ResultBuilder.failed(Messages.ERROR.DISABLED);
        }
        return this.addWord(Methods.WHITELIST.ADD, word);
    }

    public Result<Void> removeWord(String word) {
        if (!isEnabled()) {
            return ResultBuilder.failed(Messages.ERROR.DISABLED);
        }
        return this.removeWord(Methods.WHITELIST.REMOVE, word);
    }

    public Result<List<String>> listWords() {
        if (!isEnabled()) {
            return ResultBuilder.failed(Messages.ERROR.DISABLED);
        }
        return this.listWords(Methods.WHITELIST.GET);
    }

}
