package com.lindar.webpurify.util;

public interface Methods {

    String ROOT    = "webpurify.live.";
    String RETURN  = ROOT + "return";
    String REPLACE = ROOT + "replace";
    String CHECK   = ROOT + "check";
    String COUNT   = ROOT + "checkcount";

    interface BLACKLIST {

        String GET    = ROOT + "getblacklist";
        String ADD    = ROOT + "addtoblacklist";
        String REMOVE = ROOT + "removefromblacklist";

    }

    interface WHITELIST {

        String GET    = ROOT + "getwhitelist";
        String ADD    = ROOT + "addtowhitelist";
        String REMOVE = ROOT + "removefromwhitelist";

    }


}
