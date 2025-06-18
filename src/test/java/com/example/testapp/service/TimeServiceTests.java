package com.example.testapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;

import com.example.testapp.persistence.TimeHistory;
import com.example.testapp.persistence.TimeHistoryPersistenceAdapter;

import java.sql.Timestamp;
import java.time.Instant;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@DataJpaTest
@Import( TimeService.class )
public class TimeServiceTests {

    @Autowired
    private TimeService subject;

    @SpyBean
    private TimeHistoryPersistenceAdapter timeHistoryPersistenceAdapter;

    @MockBean
    private TimeGenerator mockTimeGenerator;
    @Test
    void testTimeService() {

        Instant fakeInstant = Instant.now();
        when( this.mockTimeGenerator.generate() ).thenReturn( fakeInstant );

        this.subject.getCurrentTime();


        verify( this.mockTimeGenerator ).generate();
        verify( this.timeHistoryPersistenceAdapter, times( 1 ) ).save( new TimeHistory( 1L, Timestamp.from( fakeInstant ) ) );
        verifyNoMoreInteractions( this.mockTimeGenerator, this.timeHistoryPersistenceAdapter );

    }

}
