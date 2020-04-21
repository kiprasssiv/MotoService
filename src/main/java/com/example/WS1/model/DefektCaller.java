package com.example.WS1.model;


import com.example.WS1.controller.exception.DefektNotFoundException;
import com.example.WS1.controller.exception.ExternalApiException;
import com.example.WS1.controller.request.DefectServiceRequest;
import org.springframework.http.*;
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
            serverResponse = restTemplate.exchange("http://192.168.99.100:5000/defects", HttpMethod.GET, httpEntity, DefectEntity[].class);
        }  catch (HttpClientErrorException e) {
            System.out.println("Can not find defect list");
        }

        List<DefectEntity> defects = Arrays.asList(serverResponse.getBody());
        return defects;
    }
    public void removeDefect(Long defectId) {
        this.httpEntity = new HttpEntity<>("body", httpHeaders);
        try {
            restTemplate.exchange("http://192.168.99.100:5000/defects/" + defectId.toString(), HttpMethod.DELETE, httpEntity, DefectEntity.class);
        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case NOT_FOUND:
                    throw new DefektNotFoundException();
                case INTERNAL_SERVER_ERROR:
                    throw ExternalApiException.create();
            }
        }
    }

    public ResponseEntity<DefectEntity> addDefectToList(DefectServiceRequest request){
        this.httpEntity = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<DefectEntity> defectsResponse;
        try {
            defectsResponse = restTemplate.exchange("http://192.168.99.100:5000/defects/", HttpMethod.POST, httpEntity, DefectEntity.class);

        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case NOT_FOUND:
                    throw new DefektNotFoundException();
                case INTERNAL_SERVER_ERROR:
                    throw ExternalApiException.create();
                default:
                    throw e;
            }
        }
        System.out.println(defectsResponse);
        return defectsResponse;
    }
}
