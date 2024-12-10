package com.broadcom.springconsulting.testapp.persistence;

import org.springframework.data.repository.CrudRepository;

interface TimeHistoryRepository extends CrudRepository<TimeHistory, Long> {
}
