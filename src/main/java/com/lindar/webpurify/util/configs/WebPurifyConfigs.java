package com.lindar.webpurify.util.configs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebPurifyConfigs {
    private boolean enabled;
    private String url;
    private String apiKey;
}
