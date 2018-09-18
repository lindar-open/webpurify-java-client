package com.lindar.purify.api.admin;

import com.lindar.purify.util.Methods;
import com.lindar.purify.util.WebPurifyConfigs;
import com.lindar.wellrested.vo.Result;

import java.util.List;

public class BlackListResource extends AbstractAdminResource {

    public BlackListResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public Result<Void> addWord(String word){
        return this.addWord(Methods.BLACKLIST.ADD , word);
    }

    public Result<Void> removeWord(String word){
        return this.removeWord(Methods.BLACKLIST.REMOVE , word);
    }

    public Result<List<String>> listWords(){
        return this.listWords(Methods.BLACKLIST.GET);
    }

}
