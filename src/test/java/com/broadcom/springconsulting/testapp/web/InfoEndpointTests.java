package com.broadcom.springconsulting.testapp.web;

import com.broadcom.springconsulting.testapp.service.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( controllers = InfoEndpoint.class )
public class InfoEndpointTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimeService mockTimeService;

    @Test
    void testTime() throws Exception {

        Instant fakeInstant = Instant.now();
        when( this.mockTimeService.getCurrentTime() ).thenReturn( fakeInstant );

        this.mockMvc.perform( get( "/info/time" ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.timestamp", is( fakeInstant.toString() )) );

        verify( this.mockTimeService ).getCurrentTime();
        verifyNoMoreInteractions( this.mockTimeService );

    }

}
