package com.example.testapp.service;

import java.time.Instant;

public class CurrentTimeDomainModel {

    private final Long id;

    private final Instant currentTime;

    public CurrentTimeDomainModel( Long id, Instant currentTime) {

        this.id = id;
        this.currentTime = currentTime;

    }

    public Long getId() {

        return id;
    }

    public Instant getCurrentTime() {

        return currentTime;
    }

    @Override
    public String toString() {

        return "CurrentTimeDomainModel{" +
                "id=" + id +
                ", currentTime=" + currentTime +
                '}';
    }

}
