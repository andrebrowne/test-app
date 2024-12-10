package com.broadcom.springconsulting.testapp.persistence;

import com.broadcom.springconsulting.persistence.PersistenceAdapter;

@PersistenceAdapter
public class TimeHistoryPersistenceAdapter {

    private final TimeHistoryRepository repository;

    public TimeHistoryPersistenceAdapter( final TimeHistoryRepository repository ) {

        this.repository = repository;

    }

    public TimeHistory save(final TimeHistory timeHistory) {

        return repository.save( timeHistory );
    }

}
