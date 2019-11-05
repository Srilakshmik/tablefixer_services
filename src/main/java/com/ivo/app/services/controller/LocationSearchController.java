package com.ivo.app.services.controller;

import com.ivo.app.services.domain.AddressSearchResponse;
import com.ivo.app.services.domain.LocationSearchRequest;
import com.ivo.app.services.domain.LocationSearchResponse;
import com.ivo.app.services.service.LocationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/location")
public class LocationSearchController {

    @Autowired
    private LocationSearchService locationSearchService;

    @PostMapping(value = "/search/{userUUID}")
    public ResponseEntity<List<LocationSearchResponse>> searchLocations(@RequestBody @Valid LocationSearchRequest locationSearchRequest, @PathVariable(value = "userUUID") String userUUID, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<LocationSearchResponse> listLocations = locationSearchService.searchLocations(userUUID, locationSearchRequest, pageable);
        return ResponseEntity.ok().body(listLocations);
    }

    @GetMapping(value = "/search/city/{userUUID}")
    public ResponseEntity<List<AddressSearchResponse>> searchAddress(@RequestParam String searchKey, @PathVariable(value = "userUUID") String userUUID, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<AddressSearchResponse> listAddresses = locationSearchService.searchAddress(searchKey, userUUID, pageable);
        return ResponseEntity.ok().body(listAddresses);
    }

}