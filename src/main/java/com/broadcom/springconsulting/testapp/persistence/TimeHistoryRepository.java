package com.broadcom.springconsulting.testapp.persistence;

import com.broadcom.springconsulting.persistence.PersistenceAdapter;
import org.springframework.data.repository.CrudRepository;

@PersistenceAdapter
public interface TimeHistoryRepository extends CrudRepository<TimeHistory, Long> {
}
