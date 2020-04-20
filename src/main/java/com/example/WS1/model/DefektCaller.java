package com.example.WS1.model;

import com.example.WS1.controller.exception.DefektNotFoundException;
import com.example.WS1.controller.request.DefektRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;




public class DefektCaller {
    @Value("${defect-service.url}") // Base url
    private String baseDefectResourceUrl;

    @Value("${defect-service.url.all}")
    private String defectResourceUrlAll;

    @Value("${defect-service.url.one}")
    private String defectResourceUrlOne;

    private static String url = "/defects";
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private HttpEntity<Object> httpEntity;

    public DefektCaller(){
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
            new DefektNotFoundException();
        }

        List<DefectEntity> defects = Arrays.asList(serverResponse.getBody());
        return defects;
    }
}
