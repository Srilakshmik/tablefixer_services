package com.ivo.app.services.service;

import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.EventDetailsResponse;
import com.ivo.app.services.domain.UpdateEventRequest;

public interface EventService {
    EventDetailsResponse saveEvent(EventDetailRequest event, String userUUID);

    EventDetailsResponse updateEvent(UpdateEventRequest event, String userUUID);
}
