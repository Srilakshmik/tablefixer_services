package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.PublicEventDao;
import com.ivo.app.services.domain.PublicEventRequest;
import com.ivo.app.services.domain.PublicEventResponse;
import com.ivo.app.services.service.PublicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicEventServiceImpl implements PublicEventService {

    @Autowired
    private PublicEventDao publicEventDao;

    @Override
    public List<PublicEventResponse> getOpenEvents(PublicEventRequest request, Pageable pageable) {
        return publicEventDao.getOpenEvents(request, pageable);
    }
}
