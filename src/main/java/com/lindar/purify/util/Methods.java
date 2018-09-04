package com.lindar.purify.util;

public interface Methods {

    String ROOT = "webpurify.live";

    interface BLACKLIST{

        String GET = ROOT + ".getblacklist";
        String ADD = ROOT +".addtoblacklist";
        String REMOVE = ROOT + ".removefromblacklist";

    }

    interface WHITELIST{

        String GET = ROOT + ".getwhitelist";
        String ADD = ROOT + ".addtowhitelist";
        String REMOVE =ROOT + ".removefromwhitelist";

    }

}
