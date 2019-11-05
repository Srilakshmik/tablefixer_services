package com.ivo.app.services.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class EventDetailsResponse extends EventDetailRequest implements Serializable {

    @ApiModelProperty(hidden = true)
    private String eventUUID;

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }
}
