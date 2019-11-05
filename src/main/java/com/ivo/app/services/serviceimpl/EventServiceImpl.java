package com.ivo.app.services.serviceimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.dao.LocationSearchDao;
import com.ivo.app.services.domain.EventDetailRequest;
import com.ivo.app.services.domain.EventDetailsResponse;
import com.ivo.app.services.domain.LocationDetails;
import com.ivo.app.services.domain.UpdateEventRequest;
import com.ivo.app.services.entity.EventDetailsEntity;
import com.ivo.app.services.entity.UserInfoRef;
import com.ivo.app.services.repository.EventDetailsTransRepository;
import com.ivo.app.services.repository.UserInfoRefRepository;
import com.ivo.app.services.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LogManager.getLogger(EventServiceImpl.class);

    @Autowired
    private LocationSearchDao locationSearchDao;

    @Autowired
    private EventDetailsTransRepository eventDetailsTransRepository;

    @Autowired
    private UserInfoRefRepository userInfoRefRepository;

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional
    public EventDetailsResponse saveEvent(EventDetailRequest event, String userUUID) {

        EventDetailRequest enrichedEvent = enrichEventDetails(event);

        EventDetailsResponse response = new EventDetailsResponse();

        peformStaticValidationOnNewEvent(enrichedEvent, userUUID);

        performLookupValidationOnEvent(enrichedEvent, userUUID);

        LocationDetails locationDetails = locationSearchDao.getLocationDetailsByLocationUUID(event.getLocationUUID());

        EventDetailsEntity eventDetailsEntity = setEventDetails(event, userUUID, locationDetails);
        EventDetailsEntity savedEventDetailsEntity = null;
        try {
            savedEventDetailsEntity = eventDetailsTransRepository.save(eventDetailsEntity);
            response = setEventDetailsResponse(savedEventDetailsEntity, response, event);
//            response=(EventDetailsResponse)event;
        } catch (DataIntegrityViolationException integrityException) {
            if(integrityException.getMessage()!=null) {
                if (integrityException.getMessage().contains("event_details_trans_fk_user_uuid")) {
                    System.out.println("==>1");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'userUUID'");
                } else if (integrityException.getMessage().contains("event_details_trans_pay_pref_id")) {
                    System.out.println("==>2");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'payPrefId'");
                } else if (integrityException.getMessage().contains("event_details_trans_evnt_purpose_id")) {
                    System.out.println("3");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'eventPurposeId'");
                } else if (integrityException.getMessage().contains("event_details_trans_gender_id")) {
                    System.out.println("4");
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'eventGenderPrefId'");
                }
            }
        } catch (Exception exception) {
            System.out.println("!!!"+exception);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create Event");
        }
        logger.info("Event ID =" + savedEventDetailsEntity.getEventId());
        response.setEventUUID(savedEventDetailsEntity.getEventUUID());
        return response;
    }

    private EventDetailsResponse setEventDetailsResponse(EventDetailsEntity savedEventDetailsEntity, EventDetailsResponse response, EventDetailRequest request) {
        response.setEventUUID(savedEventDetailsEntity.getEventUUID());
        response.setEventEndTime(savedEventDetailsEntity.getEventToDttm());
        response.setEventDurationMinutes(request.getEventDurationMinutes());
        response.setEventGenderPrefId(savedEventDetailsEntity.getEventGenderCatId());
        response.setEventGuestExpectedAgeEnd(savedEventDetailsEntity.getEventAgeBracketEndYrs());
        response.setEventGuestExpectedAgeStart(savedEventDetailsEntity.getEventAgeBracketStartYrs());
        response.setEventPurposeId(savedEventDetailsEntity.getEventPurposeId());
        response.setEventStartTime(savedEventDetailsEntity.getEventFromDttm());
        response.setLocationUUID(savedEventDetailsEntity.getEventLocUUID());
        response.setMaxGuestsAllowed(savedEventDetailsEntity.getMaxGuestsAllowed());
        response.setPayPrefId(savedEventDetailsEntity.getPaySharePrefId());
        response.setPrivate(savedEventDetailsEntity.getPrivate());
        response.setReservationUnderName(request.getReservationUnderName());
        response.setUserCurrentTimeZone(request.getUserCurrentTimeZone());
        response.setEventDesc(savedEventDetailsEntity.getEventDesc());
        return response;
    }

    @Override
    public EventDetailsResponse updateEvent(UpdateEventRequest event, String userUUID) {

        performStaticValidationOnExistingEvent(event,userUUID);

        EventDetailsEntity eventDetailsEntity =eventDetailsTransRepository.findEventByEventUUID(event.getEventUUID());

        if(eventDetailsEntity==null || eventDetailsEntity.getEventUUID()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No event found for the give 'eventUUID'");
        }

        if(!userUUID.equalsIgnoreCase(eventDetailsEntity.getOrganizerUUID())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'userUUID' is not authorized to modify the event details");
        }

        if(eventDetailsEntity.getEventFromDttm().isBefore(LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cant modify events older than current time");
        }

        performLookupValidationOnExistingEvent( event, userUUID);

        EventDetailsEntity updatedEventDetailsEntity = updateEventDetails(event, userUUID, eventDetailsEntity);


        EventDetailsEntity updatedEntity =eventDetailsTransRepository.saveAndFlush(updatedEventDetailsEntity);
        return setEventDetails(updatedEntity);
    }

    private EventDetailsResponse setEventDetails(EventDetailsEntity updatedEntity) {

        EventDetailsResponse response = new EventDetailsResponse();
        response.setEventUUID(updatedEntity.getEventUUID());
        response.setEventEndTime(updatedEntity.getEventToDttm());
        response.setEventGenderPrefId(updatedEntity.getEventGenderCatId());
        response.setEventGuestExpectedAgeEnd(updatedEntity.getEventAgeBracketEndYrs());
        response.setEventGuestExpectedAgeStart(updatedEntity.getEventAgeBracketStartYrs());
        response.setEventPurposeId(updatedEntity.getEventPurposeId());
        response.setEventStartTime(updatedEntity.getEventFromDttm());
        response.setLocationUUID(updatedEntity.getEventLocUUID());
        response.setMaxGuestsAllowed(updatedEntity.getMaxGuestsAllowed());
        response.setPayPrefId(updatedEntity.getPaySharePrefId());
        response.setPrivate(updatedEntity.getPrivate());
//        response.setReservationUnderName(updatedEntity.getre);

        return response;
    }

    private EventDetailsEntity updateEventDetails(UpdateEventRequest event, String userUUID, EventDetailsEntity dbEventDetailsEntity) {
        EventDetailsEntity eventDetailsEntity = new EventDetailsEntity();
        eventDetailsEntity.setEventId(dbEventDetailsEntity.getEventId());
        eventDetailsEntity.setEventActive(!event.isEventCancel());
        if(!dbEventDetailsEntity.getEventLocUUID().equalsIgnoreCase(event.getLocationUUID())){
            LocationDetails locationDetails = locationSearchDao.getLocationDetailsByLocationUUID(event.getLocationUUID());
            eventDetailsEntity.setEventLocUUID(event.getLocationUUID());
            eventDetailsEntity.setEventLocationTypeID(locationDetails.getLocationTypeId());
            eventDetailsEntity.setEventAddressLn1(locationDetails.getAddrLn1());
            eventDetailsEntity.setEventAddressLn2(locationDetails.getAddrLn2());
            eventDetailsEntity.setEventCity(locationDetails.getCity());
            eventDetailsEntity.setEventState(locationDetails.getState());
            eventDetailsEntity.setEventZip(locationDetails.getZip());
            eventDetailsEntity.setEventCountry(locationDetails.getCountry());
            eventDetailsEntity.setEventLocationName(locationDetails.getLocName());
            eventDetailsEntity.setEventLongitude(locationDetails.getLongitude());
            eventDetailsEntity.setEventLatitude(locationDetails.getLatitude());
        }else{
            eventDetailsEntity.setEventLocUUID(dbEventDetailsEntity.getEventLocUUID());
            eventDetailsEntity.setEventLocationTypeID(dbEventDetailsEntity.getEventLocationTypeID());
            eventDetailsEntity.setEventAddressLn1(dbEventDetailsEntity.getEventAddressLn1());
            eventDetailsEntity.setEventAddressLn2(dbEventDetailsEntity.getEventAddressLn2());
            eventDetailsEntity.setEventCity(dbEventDetailsEntity.getEventCity());
            eventDetailsEntity.setEventState(dbEventDetailsEntity.getEventState());
            eventDetailsEntity.setEventZip(dbEventDetailsEntity.getEventZip());
            eventDetailsEntity.setEventCountry(dbEventDetailsEntity.getEventCountry());
            eventDetailsEntity.setEventLocationName(dbEventDetailsEntity.getEventLocationName());
            eventDetailsEntity.setEventLongitude(dbEventDetailsEntity.getEventLongitude());
            eventDetailsEntity.setEventLatitude(dbEventDetailsEntity.getEventLatitude());
        }

        eventDetailsEntity.setEventFromDttm(event.getEventStartTime());
        eventDetailsEntity.setEventToDttm(event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        eventDetailsEntity.setEventGenderCatId(event.getEventGenderPrefId());
        eventDetailsEntity.setEventPurposeId(event.getEventPurposeId());
        eventDetailsEntity.setPaySharePrefId(event.getPayPrefId());
        eventDetailsEntity.setMaxGuestsAllowed(event.getMaxGuestsAllowed());
        eventDetailsEntity.setEventUUID(dbEventDetailsEntity.getEventUUID());

        eventDetailsEntity.setOrganizerUUID(userUUID);
        eventDetailsEntity.setPrivate(false);
        eventDetailsEntity.setInsrtBy(userUUID);
        eventDetailsEntity.setUpdtBy(userUUID);

        eventDetailsEntity.setEventAgeBracketStartYrs(event.getEventGuestExpectedAgeStart());
        eventDetailsEntity.setEventAgeBracketEndYrs(event.getEventGuestExpectedAgeEnd());
        return eventDetailsEntity;
    }

    private void peformStaticValidationOnNewEvent(EventDetailRequest event, String userUUID) {

        if (event.getEventStartTime().isBefore(LocalDateTime.now())) {
            System.out.println(event.getEventStartTime() + "===" + LocalDateTime.now());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be a past date");
        }
        if (event.getEventStartTime().isAfter(LocalDateTime.now().plusDays(90))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be more than 90 days from now");
        }

        if (event.getEventGuestExpectedAgeStart() > event.getEventGuestExpectedAgeEnd()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventGuestExpectedAgeStart' can't be more than 'eventGuestExpectedAgeEnd'");
        }

    }

    private void performStaticValidationOnExistingEvent(UpdateEventRequest event, String userUUID) {

        if (event.getEventStartTime().isBefore(LocalDateTime.now())) {
            System.out.println(event.getEventStartTime() + "===" + LocalDateTime.now());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be a past date | you can't modify past events");
        }
        if (event.getEventStartTime().isAfter(LocalDateTime.now().plusDays(90))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'eventStartTime' can't be more than 90 days from now");
        }

    }
    private void performLookupValidationOnEvent(EventDetailRequest event, String userUUID) {
        UserInfoRef userInfoRef =userInfoRefRepository.findByuserUUID(userUUID);
        if(userInfoRef==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid 'userUUID'");
        }

        if(event.getMaxGuestsAllowed()>userInfoRef.getMaxGuestsAallowedPerEvent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Only can invite upto " + userInfoRef.getMaxGuestsAallowedPerEvent() + " guests per event");
        }

        if (isEventConflictingAnyOtherExistingEvent(event.getEventStartTime(), event.getEventEndTime(), userUUID,  null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Meeting timings conflict found. Check the events");
        }

        if (getUserEventsCountByDate(userUUID, event.getEventStartTime(), null)>=userInfoRef.getDailyEventsLimit()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't care more than "+userInfoRef.getDailyEventsLimit()+ " events per day");
        }
        //Lookup Validations end
    }

    private void performLookupValidationOnExistingEvent(UpdateEventRequest event, String userUUID) {
        UserInfoRef userInfoRef =userInfoRefRepository.findByuserUUID(userUUID);
        if(userInfoRef==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid 'userUUID'");
        }

        if(event.getMaxGuestsAllowed()>userInfoRef.getMaxGuestsAallowedPerEvent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Only can invite upto " + userInfoRef.getMaxGuestsAallowedPerEvent() + " guests per event");
        }

        if (isEventConflictingAnyOtherExistingEvent(event.getEventStartTime(), event.getEventEndTime(), userUUID,event.getEventUUID())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Meeting timings conflict found. Check the events");
        }

        if (getUserEventsCountByDate(userUUID, event.getEventStartTime(),event.getEventUUID())>=userInfoRef.getDailyEventsLimit()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't care more than "+userInfoRef.getDailyEventsLimit()+ " events per day");
        }
        //Lookup Validations end
    }


    private Integer getUserEventsCountByDate(String userUUID, LocalDateTime eventStartTime,String eventUUID) {
        return eventDao.getUserEventsCountByDate(userUUID, eventStartTime, eventUUID);
    }

    private boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String userUUID, String eventUUID) {
        return eventDao.isEventConflictingAnyOtherExistingEvent(eventStartTime, eventEndTime, userUUID, eventUUID);
    }


    private EventDetailRequest enrichEventDetails(EventDetailRequest event) {
        event.setEventEndTime(event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        return event;
    }



    private EventDetailsEntity setEventDetails(EventDetailRequest event, String userUUID, LocationDetails locationDetails) {
        EventDetailsEntity eventDetailsEntity = new EventDetailsEntity();
        eventDetailsEntity.setEventActive(true);
        eventDetailsEntity.setEventAddressLn1(locationDetails.getAddrLn1());
        eventDetailsEntity.setEventAddressLn2(locationDetails.getAddrLn2());
        eventDetailsEntity.setEventCity(locationDetails.getCity());
        eventDetailsEntity.setEventState(locationDetails.getState());
        eventDetailsEntity.setEventZip(locationDetails.getZip());
        eventDetailsEntity.setEventCountry(locationDetails.getCountry());
        eventDetailsEntity.setEventFromDttm(event.getEventStartTime());
        eventDetailsEntity.setEventToDttm(event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        eventDetailsEntity.setEventGenderCatId(event.getEventGenderPrefId());
        eventDetailsEntity.setEventPurposeId(event.getEventPurposeId());
        eventDetailsEntity.setPaySharePrefId(event.getPayPrefId());
        eventDetailsEntity.setMaxGuestsAllowed(event.getMaxGuestsAllowed());
        eventDetailsEntity.setEventUUID(UUID.randomUUID().toString());
        eventDetailsEntity.setEventLocUUID(event.getLocationUUID());
        eventDetailsEntity.setEventLocationTypeID(locationDetails.getLocationTypeId());
        eventDetailsEntity.setOrganizerUUID(userUUID);
        eventDetailsEntity.setPrivate(false);
        eventDetailsEntity.setInsrtBy(userUUID);
        eventDetailsEntity.setUpdtBy(userUUID);
        eventDetailsEntity.setEventLocationName(locationDetails.getLocName());
        eventDetailsEntity.setEventLongitude(locationDetails.getLongitude());
        eventDetailsEntity.setEventLatitude(locationDetails.getLatitude());
        eventDetailsEntity.setEventAgeBracketStartYrs(event.getEventGuestExpectedAgeStart());
        eventDetailsEntity.setEventAgeBracketEndYrs(event.getEventGuestExpectedAgeEnd());
        eventDetailsEntity.setEventDesc(event.getEventDesc());
        return eventDetailsEntity;
    }
}
