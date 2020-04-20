package com.example.WS1.model;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


public class DefektCaller {

    private URI defectResourceUrlAll;

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private HttpEntity<Object> httpEntity;

    public DefektCaller(String url) {
        this.defectResourceUrlAll = URI.create(url);
        this.restTemplate = new RestTemplate();
        this.httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public List<DefectEntity> getDefectList(){
        this.httpEntity = new HttpEntity<>("body", httpHeaders);
        ResponseEntity<DefectEntity[]> serverResponse = null;

        try {
            serverResponse = restTemplate.exchange(defectResourceUrlAll, HttpMethod.GET, httpEntity, DefectEntity[].class);
        }  catch (HttpClientErrorException e) {
            System.out.println("Can not find defect list");
        }

        List<DefectEntity> defects = Arrays.asList(serverResponse.getBody());
        return defects;
    }
}
