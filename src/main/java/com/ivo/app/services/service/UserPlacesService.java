package com.ivo.app.services.service;

import com.ivo.app.services.domain.UserPlacesResponse;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.request.UserLocationRequest;

import java.util.List;

public interface UserPlacesService {
    List<UserPlacesResponse> getUserPlaces(String userUUID);

    UserLocationsXref createLabelForMyPlaces(UserLocationRequest userLocation, String userUUID,
                                             Long useerAddrId);

    int updateLabelForMyPlaces(UserLocationRequest userLocation, Long userAddrId, String userUUID);

    long deleteLabelForMyPlaces(String userUUID, Long useerAdrId);
}