package com.ivo.app.services.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public class UpdateEventRequest extends EventDetailRequest implements Serializable {

    @NotNull
    @NotEmpty
    @ApiModelProperty(required = true)
    private String eventUUID;

    @ApiModelProperty(required = true, example = "false")
    private boolean isEventCancel;

    public String  getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    public boolean isEventCancel() {
        return isEventCancel;
    }

    public void setEventCancel(boolean eventCancel) {
        isEventCancel = eventCancel;
    }
}
