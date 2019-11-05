package com.ivo.app.services.daoimpl;

import com.ivo.app.services.dao.PublicEventDao;
import com.ivo.app.services.domain.PublicEventRequest;
import com.ivo.app.services.domain.PublicEventResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PublicEventDaoImpl implements PublicEventDao {

    private static final Logger logger = LogManager.getLogger(PublicEventDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<PublicEventResponse> getOpenEvents(PublicEventRequest request, Pageable pageable) {

        Map<String, Object> params = new HashMap<>();
        params.put("fromLatitude", Double.parseDouble(request.getFromLatitude()));
        params.put("fromLongitude", Double.parseDouble(request.getFromLongitude()));
        params.put("radius", new Float(request.getSearchRadius()));


        List<PublicEventResponse> listPublicEventResponse = namedParameterJdbcTemplate.query(" select max_guests_allowed, SUM(CASE  WHEN guest.organizer_status='Accepted' THEN 1 ELSE 0 END) AS org_accepted_cnt," +
                " SUM(CASE WHEN guest.guest_status='Interested' THEN 1 ELSE 0 END) AS guest_interested_cnt,trans.event_uuid,trans.event_location_name,trans.event_loc_uuid, " +
                " trans.event_addr_ln1,trans.event_addr_ln2,trans.event_city,trans.event_country," +
                " trans.event_state,trans.event_zip,usr.user_uuid, usr.first_name || ' ' || usr.last_name as username," +
                " usr.facebook_link, usr.twitter_link,usr.linkedin_link, usr.contact_num," +
                " trans.event_from_dttm, trans.event_to_dttm,trans.event_desc,trans.event_short_desc,trans.organizer_uuid,trans.event_lang,trans.event_lat," +
                " ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat),   ST_MakePoint(:fromLongitude, :fromLatitude)) distance" +
                " from  event_details_trans trans " +
                " inner join user_info_ref usr on trans.organizer_uuid = usr.user_uuid " +
                " left join event_guest_sign_up_trans guest on trans.event_uuid= guest.event_uuid " +
                " where  trans.event_from_dttm >now() " +
                " and ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat), ST_MakePoint(:fromLongitude, :fromLatitude))<= :radius * 1609.344 " +
                " group by max_guests_allowed,trans.event_uuid,trans.event_location_name,trans.event_loc_uuid, " +
                " trans.event_addr_ln1,trans.event_addr_ln2,trans.event_city,trans.event_country," +
                " trans.event_state,trans.event_zip,usr.user_uuid, usr.first_name ,usr.last_name ," +
                " usr.facebook_link, usr.twitter_link,usr.linkedin_link, usr.contact_num, " +
                " trans.event_from_dttm, trans.event_to_dttm,trans.event_desc,trans.event_short_desc,trans.organizer_uuid, " +
                " trans.event_lang,trans.event_lat " +
                " order by ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat),   ST_MakePoint(:fromLongitude, :fromLatitude))", params, new BeanPropertyRowMapper<>(PublicEventResponse.class));

        return listPublicEventResponse;
    }
}
