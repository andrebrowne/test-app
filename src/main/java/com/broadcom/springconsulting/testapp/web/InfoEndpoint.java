package com.broadcom.springconsulting.testapp.web;

import com.broadcom.springconsulting.endpoint.EndpointAdapter;
import com.broadcom.springconsulting.testapp.service.TimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@EndpointAdapter
@RequestMapping( "/info" )
public class InfoEndpoint {

    private final TimeService timeService;

    public InfoEndpoint( final TimeService timeService ) {
        this.timeService = timeService;
    }

    @GetMapping( "/time" )
    public TimeResponse time() {

        var ts = this.timeService.getCurrentTime();

        return new TimeResponse( ts.toString() );
    }

}
