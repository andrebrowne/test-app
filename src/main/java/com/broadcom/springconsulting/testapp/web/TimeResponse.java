package com.broadcom.springconsulting.testapp.web;

public class TimeResponse {

    private String timestamp;

    public TimeResponse( final String timestamp ) {

        this.timestamp = timestamp;

    }

    public String getTimestamp() {

        return timestamp;
    }

    public void setTimestamp( final String timestamp ) {

        this.timestamp = timestamp;

    }

}
