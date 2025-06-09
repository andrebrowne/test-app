package com.example.testapp.service;

import com.example.testapp.persistence.TimeHistory;
import com.example.testapp.persistence.TimeHistoryPersistenceAdapter;
import com.example.usecase.UseCase;

import java.sql.Timestamp;
import java.time.Instant;

@UseCase
public class TimeService {

    private final TimeGenerator timeGenerator;
    private final TimeHistoryPersistenceAdapter persistenceAdapter;

    public TimeService( final TimeGenerator timeGenerator, final TimeHistoryPersistenceAdapter persistenceAdapter ) {

        this.timeGenerator = timeGenerator;
        this.persistenceAdapter = persistenceAdapter;

    }

    public CurrentTimeDomainModel getCurrentTime() {

        Instant now = this.timeGenerator.generate();

        TimeHistory created = this.persistenceAdapter.save( new TimeHistory(null, Timestamp.from( now ) ) );

        return new CurrentTimeDomainModel( created.getId(), created.getTimestamp().toInstant() );
    }

}
