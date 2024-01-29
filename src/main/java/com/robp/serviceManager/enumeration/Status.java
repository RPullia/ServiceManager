package com.robp.serviceManager.enumeration;

public enum Status {
    SERVICE_UP("SERVICE_UP"),
    SERVICE_DOWN("SERVICE_DOWN");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
