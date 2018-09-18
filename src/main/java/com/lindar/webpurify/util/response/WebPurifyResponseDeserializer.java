package com.lindar.webpurify.util.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lindar.acolyte.util.ListsAcolyte;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WebPurifyResponseDeserializer implements JsonDeserializer<WebPurifyInnerResponse> {

    @Override public WebPurifyInnerResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        WebPurifyInnerResponse webPurifyInnerResponse = new WebPurifyInnerResponse();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (jsonObject.has("success")) {
            webPurifyInnerResponse.setSuccess(jsonObject.get("success").getAsInt());
        }

        if (jsonObject.has("text")) {
            webPurifyInnerResponse.setText(jsonObject.get("text").getAsString());
        }

        if (jsonObject.has("found")) {
            webPurifyInnerResponse.setWordsFound(jsonObject.get("found").getAsInt());
        }

        if (jsonObject.has("word") && jsonObject.get("word").isJsonArray()) {
            webPurifyInnerResponse.setWords(toListOfStrings(jsonObject.getAsJsonArray("word")));
        } else if (jsonObject.has("word")) {
            webPurifyInnerResponse.setWords(ListsAcolyte.listOf(jsonObject.get("word").getAsString()));
        }

        if (jsonObject.has("expletive") && jsonObject.get("expletive").isJsonArray()) {
            webPurifyInnerResponse.setProfanityList(toListOfStrings(jsonObject.getAsJsonArray("expletive")));
        } else if (jsonObject.has("expletive")) {
            webPurifyInnerResponse.setProfanityList(ListsAcolyte.listOf(jsonObject.get("expletive").getAsString()));
        }
        return webPurifyInnerResponse;
    }

    private List<String> toListOfStrings(JsonArray jsonElements) {
        List<String> strings = new ArrayList<>();
        jsonElements.forEach(jsonElement -> {
            strings.add(jsonElement.getAsString());
        });
        return strings;
    }

}


