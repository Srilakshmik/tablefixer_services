package com.ivo.app.services.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class EventDetailRequest implements Serializable {

    @NotNull
    @ApiModelProperty(required = true, example = "2019-09-29 14:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventStartTime;

    @ApiModelProperty(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventEndTime;

    @NotNull(message = "'locationUUID' is required")
    @NotEmpty(message = "'locationUUID' is required")
    @ApiModelProperty(required = true)
    private String locationUUID;

    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    @ApiModelProperty(required = true)
    private Integer payPrefId;

    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 1)
    @Max(value = 100)
    private Integer eventPurposeId;

    @NotNull
    @Min(value = 18)
    @Max(value = 120)
    @ApiModelProperty
    private Integer eventGuestExpectedAgeStart;

    @NotNull
    @ApiModelProperty
    @Min(value = 18)
    @Max(value = 120)
    private Integer eventGuestExpectedAgeEnd;

    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 1)
    @Max(value = 3)
    private Integer eventGenderPrefId;

    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 1)
    @Max(value = 100)
    private Integer maxGuestsAllowed;

    private String reservationUnderName;

    @NotNull
    @ApiModelProperty(required = true)
    @Min(value = 10)
    @Max(value = 24 * 60)
    private Integer eventDurationMinutes;

    @NotNull
    @ApiModelProperty(required = true)
    private Boolean isPrivate;

    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String userCurrentTimeZone;

    @NotEmpty(message = "'eventDesc' can't be empty")
    @ApiModelProperty(required = true)
    @Size(min = 10, max = 500)
    private String eventDesc;

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalDateTime eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getLocationUUID() {
        return locationUUID;
    }

    public void setLocationUUID(String locationUUID) {
        this.locationUUID = locationUUID;
    }

    public Integer getPayPrefId() {
        return payPrefId;
    }

    public void setPayPrefId(Integer payPrefId) {
        this.payPrefId = payPrefId;
    }

    public Integer getEventPurposeId() {
        return eventPurposeId;
    }

    public void setEventPurposeId(Integer eventPurposeId) {
        this.eventPurposeId = eventPurposeId;
    }

    public Integer getEventGenderPrefId() {
        return eventGenderPrefId;
    }

    public void setEventGenderPrefId(Integer eventGenderPrefId) {
        this.eventGenderPrefId = eventGenderPrefId;
    }

    public Integer getMaxGuestsAllowed() {
        return maxGuestsAllowed;
    }

    public void setMaxGuestsAllowed(Integer maxGuestsAllowed) {
        this.maxGuestsAllowed = maxGuestsAllowed;
    }

    public String getReservationUnderName() {
        return reservationUnderName;
    }

    public void setReservationUnderName(String reservationUnderName) {
        this.reservationUnderName = reservationUnderName;
    }

    public Integer getEventDurationMinutes() {
        return eventDurationMinutes;
    }

    public void setEventDurationMinutes(Integer eventDurationMinutes) {
        this.eventDurationMinutes = eventDurationMinutes;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getUserCurrentTimeZone() {
        return userCurrentTimeZone;
    }

    public void setUserCurrentTimeZone(String userCurrentTimeZone) {
        this.userCurrentTimeZone = userCurrentTimeZone;
    }

    public Integer getEventGuestExpectedAgeStart() {
        return eventGuestExpectedAgeStart;
    }

    public void setEventGuestExpectedAgeStart(Integer eventGuestExpectedAgeStart) {
        this.eventGuestExpectedAgeStart = eventGuestExpectedAgeStart;
    }

    public Integer getEventGuestExpectedAgeEnd() {
        return eventGuestExpectedAgeEnd;
    }

    public void setEventGuestExpectedAgeEnd(Integer eventGuestExpectedAgeEnd) {
        this.eventGuestExpectedAgeEnd = eventGuestExpectedAgeEnd;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }
}
