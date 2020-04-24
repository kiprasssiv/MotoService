package com.example.WS1.configuration;

import com.example.WS1.model.DefectCaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "defect")
@Validated
class DefectTemplateProperties {
       private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

@Configuration
public class DefectTemplateConfig {
    @Bean
    public DefectCaller defektCaller(
            @Value("${defect.url}") String url
    ) {
        return new DefectCaller();
    }
}
