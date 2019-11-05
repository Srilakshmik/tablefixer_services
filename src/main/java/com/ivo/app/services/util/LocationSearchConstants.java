package com.ivo.app.services.util;

public interface LocationSearchConstants {

    String GEO_COORDINATES = "GEO_COORDINATES";

    String USER_LOCATIONS = "USER_LOCATIONS";

    String SEARCH_KEYWORD = "SEARCH_KEYWORD";

	String CURRENT_GPS_LOCATION = "CURRENT_GPS_LOCATION";

	String USER_MY_PLACES = "USER_MY_PLACES";

	String CUSTOM_GPS_LOCATION = "CUSTOM_GPS_LOCATION";

	String QUERY_SEARCH_LOCATIONS_BY_GPS_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, "
			+ " loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country " +
			" from  location_info_ref loc " +
			" where " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <= :radius * 1609.344 ";

	String QUERY_SEARCH_BY_CUISINE_TYPES = "select loc.location_id locationId, loc.loc_uuid locationUUID, " +
			" loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, " +
			" loc.city city,loc.state state, loc.zip_code zip,loc.country country  " +
			" from  location_info_ref loc , location_tags_ref lt, location_tag_xref ltxref " +
			" where  " +
			" loc.loc_uuid=ltxref.loc_uuid " +
			" and lt.tag_id=ltxref.loc_tag_id " +
			" and Upper(lt.tag_name) like upper(:cuisineType) " +
			" and ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) " +
			" <= :radius * 1609.344 ";

	String QUERY_ADD_SEARCH_BY_RESTAURANT_NAME = " and upper(loc.loc_name) like upper(:locationName) ";


	String QUERY_ADD_ORDER_BY_DISTANCE_LOCATION_TABLE_REFERENCE = " order by ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) ";

	String QUERY_USER_FAVORITE_LOCATIONS_BY_GPS_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, "
			+ " loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite " +
			" from  location_info_ref loc, user_favorite_locations_xref favorite" +
			" where " +
			" loc.loc_uuid=favorite.location_uuid" +
			" and favorite.user_uuid=:userUuid " +
			" and ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) ";
	String QUERY_USER_FAVORITE_LOCATIONS_BY_DEFAULT = "select loc.location_id locationId, loc.loc_uuid locationUUID, " +
            " loc.loc_name locationName,  contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2,\n" +
            " loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite " +
            " from  location_info_ref loc, user_favorite_locations_xref favorite " +
            " where loc.loc_uuid=favorite.location_uuid " +
            " and favorite.user_uuid=:userUuid ";

String USER_LOCATION_UPDATE="UPDATE user_locations_xref  SET user_location_name = :userLocationName WHERE user_addr_id = :userAdrId";

String QUERY_USER_FAVORITE_LOCATIONS_BY_BOOK_MARKED_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.longitude,userloc.latitude), "+
        " ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber , loc.email email, loc.website ,loc.address_ln1 addrLn1 ,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite "+
        " from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc , user_favorite_locations_xref favorite "+
        " where "+
        " favorite.user_uuid=usr.user_uuid "+
		" and favorite.location_uuid=loc.loc_uuid  and userloc.user_uuid=favorite.user_uuid" +
        " and usr.user_uuid=:userUuid "+
        " and upper(userloc.user_location_name)=:userLocationType "+
        " and ST_DistanceSphere(ST_MakePoint(userloc.longitude,userloc.latitude), "+
        " ST_MakePoint(loc.lang,loc.lat)) "+
        " <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.longitude,userloc.latitude), "+
        " ST_MakePoint(loc.lang,loc.lat))";


String QUERY_GENERIC_ADDRESS_SEARCH = "select  name,county,state,zip_codes,longitude,latitude " +

			" from us_cities " +
			" where upper(name) like upper(:searchKey)  or upper(county) like upper(:searchKey) or zip_codes like :searchKey";
}
