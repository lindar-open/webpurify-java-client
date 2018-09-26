package com.lindar.webpurify.util;

public interface Messages {

    interface ERROR {
        String API_KEY_INVALID     = "Invalid API Key";
        String API_KEY_INACTIVE    = "API Key is inactive";
        String API_KEY_MISSING     = "API Key was not included in request";
        String SERVICE_UNAVAILABLE = "Service currently unavailable";
        String EXCEEDED_CALL_LIMIT = "You have exceeded the number of simultaneous requests for your license";
        String WORD_EMPTY          = "Word parameter is blank";
        String UNKNOWN             = "Unknown error";
        String DISABLED            = "WebPurify client is disabled. Please enable in configs";
    }

    interface SUCCESS {
        String WORD_ADDED    = "Word added";
        String WORD_REMOVED  = "Word removed";
        String WORDS_FETCHED = "Words fetched";
        String TEXT_CHECKED  = "Text checked";
        String TEXT_REPLACED = "Text replaced";
        String TEXT_COUNTED  = "Text counted";
    }

}
