package com.broadcom.springconsulting.testapp.service;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TimeGenerator {

    public Instant generate() {

        return Instant.now();
    }

}
