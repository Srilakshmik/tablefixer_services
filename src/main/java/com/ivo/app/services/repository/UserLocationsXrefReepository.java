package com.ivo.app.services.repository;

import com.ivo.app.services.domain.UserPlacesResponse;
import com.ivo.app.services.entity.UserLocationsXref;
import com.ivo.app.services.util.LocationSearchConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserLocationsXrefReepository extends JpaRepository<UserLocationsXref, Long> {

    @Transactional
    @Modifying
    @Query(value = LocationSearchConstants.USER_LOCATION_UPDATE,nativeQuery = true)
    int updateUserLocation(@Param("userAdrId") Long userAdrId, @Param("userLocationName") String userLocationName);

    @Transactional
    long deleteByuserUuidAndUserAddrId(String userUuid, Long userAddrId);

    int countByUserUuidAndUserLocationNameEqualsIgnoreCase(String userUUID, String locationName);

    List<UserPlacesResponse> findByUserUuidAndActiveTrue(String uuid);

}
