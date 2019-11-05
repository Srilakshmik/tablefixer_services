package com.ivo.app.services.domain;

import lombok.Value;

@Value
public class UserPlacesResponse {

    private Long userAddrId;
    private String userUuid;
    private String userLocationName;
    private Boolean active;
    private Float longitude;
    private Float latitude;
    private String addrLn1;
    private String addrLn2;
    private String zip;
    private String city;
    private String state;
    private String country;
}
