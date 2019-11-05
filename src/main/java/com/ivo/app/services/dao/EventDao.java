package com.ivo.app.services.dao;

import com.ivo.app.services.domain.UpdateEventRequest;


import java.time.LocalDateTime;

public interface EventDao {

    int updateEvent(UpdateEventRequest event, String userUUID);

    boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime localDateTime, String userUUID, String eventUUID);

    Integer getUserEventsCountByDate(String userUUID, LocalDateTime eventStartTime,String eventUUID);

}
