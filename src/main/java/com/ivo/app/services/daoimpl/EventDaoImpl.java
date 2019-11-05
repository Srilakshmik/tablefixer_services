package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.EventDao;
import com.ivo.app.services.domain.UpdateEventRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EventDaoImpl implements EventDao {

    private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional
    public int updateEvent(UpdateEventRequest event, String userUUID) {
        Map<String, Object> params = new HashMap<>();
        params.put("eventUUID", event.getEventUUID().toString());
        params.put("maxGuestsAllowed", event.getMaxGuestsAllowed());
        params.put("eventFromDttm", event.getEventStartTime());
        params.put("eventEndDttm", event.getEventStartTime().plusMinutes(event.getEventDurationMinutes()));
        params.put("paySharePrefId", event.getPayPrefId());
        params.put("eventGenderCatId", event.getEventGenderPrefId());
        params.put("eventPurposeId", event.getEventPurposeId());

        return namedParameterJdbcTemplate.update("UPDATE public.event_details_trans " +
                " SET max_guests_allowed= :maxGuestsAllowed, event_from_dttm=:eventFromDttm, event_to_dttm=:eventEndDttm, pay_share_pref_id=:paySharePrefId, " +
                " event_gender_cat_id=:eventGenderCatId, event_purpose_id=:eventPurposeId where event_uuid=:eventUUID", params);

    }

    @Override
    public boolean isEventConflictingAnyOtherExistingEvent(LocalDateTime eventStartTime, LocalDateTime eventEndTime, String userUUID, String eventUUID) {

        Map<String, Object> params = new HashMap<>();
        params.put("userUUID", userUUID);
        params.put("eventStartTime", eventStartTime);
        params.put("eventEndTime", eventEndTime);

        StringBuilder query = new StringBuilder("select count(*) from event_details_trans where organizer_uuid=:userUUID and ((:eventStartTime between event_from_dttm   and event_to_dttm) or (:eventEndTime between event_from_dttm   and event_to_dttm )) and is_event_active=true ");
        if(eventUUID!=null){
            params.put("eventUUID",eventUUID);
            query.append(" and event_uuid <> :eventUUID ");
        }


        Integer existingEventsCount = namedParameterJdbcTemplate.queryForObject(query.toString(), params, Integer.class);
        if (existingEventsCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer getUserEventsCountByDate(String userUUID, LocalDateTime eventStartTime, String eventUUID) {

        Map<String, Object> params = new HashMap<>();
        params.put("userUUID", userUUID);
        params.put("eventStartTime", eventStartTime);

        StringBuilder query = new StringBuilder("select COUNT(*) events_cnt from event_details_trans  where organizer_uuid=:userUUID and TO_CHAR(event_from_dttm, 'YYYY-MM-DD')= TO_CHAR(:eventStartTime, 'YYYY-MM-DD') ");

        if(eventUUID!=null){
            params.put("eventUUID",eventUUID);
            query.append(" and event_uuid <> :eventUUID ");
        }

        return namedParameterJdbcTemplate.queryForObject(query.toString(), params, Integer.class);

    }

}

