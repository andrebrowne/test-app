package com.broadcom.springconsulting.testapp.service;

import com.broadcom.springconsulting.testapp.persistence.TimeHistory;
import com.broadcom.springconsulting.testapp.persistence.TimeHistoryRepository;
import com.broadcom.springconsulting.usecase.UseCase;

import java.sql.Timestamp;
import java.time.Instant;

@UseCase
public class TimeService {

    private final TimeGenerator timeGenerator;
    private final TimeHistoryRepository repository;

    public TimeService( final TimeGenerator timeGenerator, final TimeHistoryRepository repository ) {

        this.timeGenerator = timeGenerator;
        this.repository = repository;

    }

    public Instant getCurrentTime() {

        Instant now = this.timeGenerator.generate();

        TimeHistory created = this.repository.save( new TimeHistory(null, Timestamp.from( now ) ) );

        return created.getTimestamp().toInstant();
    }

}
