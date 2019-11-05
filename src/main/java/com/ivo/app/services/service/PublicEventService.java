package com.ivo.app.services.service;

import com.ivo.app.services.domain.PublicEventRequest;
import com.ivo.app.services.domain.PublicEventResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicEventService {
    List<PublicEventResponse> getOpenEvents(PublicEventRequest request, Pageable pageable);
}
