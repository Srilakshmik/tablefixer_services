package com.ivo.app.services.controller;

import com.ivo.app.services.domain.PublicEventRequest;
import com.ivo.app.services.domain.PublicEventResponse;
import com.ivo.app.services.service.PublicEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/open/event/public")
public class PublicEventController {

    @Autowired
    private PublicEventService publicEventService;

    @PostMapping(value = "/list")
    public ResponseEntity<List<PublicEventResponse>> getOpenEvents(@Valid @RequestBody PublicEventRequest request, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return new ResponseEntity<>(publicEventService.getOpenEvents(request, pageable), HttpStatus.OK);

    }

}


