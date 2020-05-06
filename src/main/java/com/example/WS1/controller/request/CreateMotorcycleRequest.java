package com.example.WS1.controller.request;


import com.example.WS1.model.enums.DefectPriority;
import com.example.WS1.model.enums.DefectStatus;

import java.util.UUID;

public class CreateMotorcycleRequest{
    private String motoMake;
    private String motoModel;
    private int motoYear;

    public String getMotoMake() {
        return motoMake;
    }

    public void setMotoMake(String motoMake) {
        this.motoMake = motoMake;
    }

    public String getMotoModel() {
        return motoModel;
    }

    public void setMotoModel(String motoModel) {
        this.motoModel = motoModel;
    }

    public int getMotoYear() {
        return motoYear;
    }

    public void setMotoYear(int motoYear) {
        this.motoYear = motoYear;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public DefectPriority getServicePriority() {
        return servicePriority;
    }

    public void setServicePriority(DefectPriority servicePriority) {
        this.servicePriority = servicePriority;
    }

    public DefectStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(DefectStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    private int serviceId;

    private String serviceName;
    private String serviceDescription;
    private DefectPriority servicePriority;
    private DefectStatus serviceStatus;


}
