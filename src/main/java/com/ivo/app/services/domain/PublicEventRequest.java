package com.ivo.app.services.domain;

import javax.validation.constraints.Digits;

public class PublicEventRequest {

    @Digits(message = "'fromLongitude' should be a decimal value", fraction = 10, integer = 10)
    private String fromLongitude;

    @Digits(message = "'fromLatitude' should be a decimal value", fraction = 10, integer = 10)
    private String fromLatitude;

    private String userUUID;

    private Integer[] listGenderIds;

    private Integer intAgeStart;

    private Integer intAgeEnd;

    private Integer[] payPrefIds;

    private String searchRadius;

    private boolean visitor;

    public String getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(String fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public String getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(String fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public Integer[] getListGenderIds() {
        return listGenderIds;
    }

    public void setListGenderIds(Integer[] listGenderIds) {
        this.listGenderIds = listGenderIds;
    }

    public Integer getIntAgeStart() {
        return intAgeStart;
    }

    public void setIntAgeStart(Integer intAgeStart) {
        this.intAgeStart = intAgeStart;
    }

    public Integer getIntAgeEnd() {
        return intAgeEnd;
    }

    public void setIntAgeEnd(Integer intAgeEnd) {
        this.intAgeEnd = intAgeEnd;
    }

    public Integer[] getPayPrefIds() {
        return payPrefIds;
    }

    public void setPayPrefIds(Integer[] payPrefIds) {
        this.payPrefIds = payPrefIds;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }

    public boolean isVisitor() {
        return visitor;
    }

    public void setVisitor(boolean visitor) {
        this.visitor = visitor;
    }
}
