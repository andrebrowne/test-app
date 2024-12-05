package com.broadcom.springconsulting.testapp.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity( name = "time_history" )
public class TimeHistory {

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp timestamp;

    public TimeHistory() { }

    public TimeHistory( Long id, Timestamp timestamp ) {

        this.id = id;
        this.timestamp = timestamp;

    }

    public Long getId() {

        return id;
    }

    public void setId( final Long id ) {

        this.id = id;

    }

    public Timestamp getTimestamp() {

        return timestamp;
    }

    public void setTimestamp( final Timestamp timestamp ) {

        this.timestamp = timestamp;

    }

    @Override
    public boolean equals( Object o ) {

        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        TimeHistory that = (TimeHistory) o;

        return Objects.equals( id, that.id );
    }

    @Override
    public int hashCode() {

        return Objects.hashCode( id );
    }

    @Override
    public String toString() {

        return "TimeHistory{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                '}';
    }

}
