package com.example.testapp.persistence;

import com.example.persistence.PersistenceAdapter;

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
