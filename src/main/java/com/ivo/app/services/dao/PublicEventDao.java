package com.ivo.app.services.dao;

import com.ivo.app.services.domain.PublicEventRequest;
import com.ivo.app.services.domain.PublicEventResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicEventDao {

    List<PublicEventResponse> getOpenEvents(PublicEventRequest request, Pageable pageable);

}
