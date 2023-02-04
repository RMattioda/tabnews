package com.rodest.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tabnews-api-config")
public class TabnewsApiConfigData {

    private String connectionUrl;
}
