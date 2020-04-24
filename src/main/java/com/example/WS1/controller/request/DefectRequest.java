package com.example.WS1.controller.request;

import java.util.UUID;

public class DefectRequest {
    private UUID moto_id;
    private int service_id;

    public UUID getMoto_id() {
        return moto_id;
    }

    public void setMoto_id(UUID moto_id) {
        this.moto_id = moto_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
}