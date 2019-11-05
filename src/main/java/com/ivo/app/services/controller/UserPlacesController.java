package com.ivo.app.services.controller;

import com.ivo.app.services.domain.UserPlacesResponse;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.request.UserLocationRequest;
import com.ivo.app.services.service.UserPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user/places")
public class UserPlacesController {

    @Autowired
    private UserPlacesService userPlacesService;

    @GetMapping(value = "/list/{userUUID}")
    public ResponseEntity<List<UserPlacesResponse>> getUserPlaces(@PathVariable(value = "userUUID") String userUUID) {
        return ResponseEntity.ok().body(userPlacesService.getUserPlaces(userUUID));
    }
    @PostMapping(value = "/create/{userUUID}")
    public ResponseEntity<UserLocationsXref> createLabelForUserPlaces(@RequestBody @Valid UserLocationRequest userLocation, @PathVariable(value = "userUUID") String userUUID) {
        return ResponseEntity.ok().body(userPlacesService.createLabelForMyPlaces(userLocation, userUUID, null));
    }
    @PutMapping(value = "/update/{userUUID}/{userAddrId}")
    public ResponseEntity updateLabelForUserPlaces(@RequestBody UserLocationRequest userLocation, @PathVariable(value = "userUUID") String userUUID,
                                                   @PathVariable(value = "userAddrId") Long userAddrId) {
        return ResponseEntity.ok().body(userPlacesService.updateLabelForMyPlaces(userLocation, userAddrId, userUUID));
    }

    @DeleteMapping(value = "/delete/{userUUID}/{userAddrId}")
    public ResponseEntity deleteLableForUserPlaces( @PathVariable(value = "userUUID") String userUUID,
                                                   @PathVariable(value = "userAddrId") Long userAddrId) {

        if (userPlacesService.deleteLabelForMyPlaces(userUUID, userAddrId) > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There is no content to delete");
        }
    }
}
