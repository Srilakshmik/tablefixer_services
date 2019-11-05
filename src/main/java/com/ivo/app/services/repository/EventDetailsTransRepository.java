package com.ivo.app.services.repository;

import com.ivo.app.services.entity.EventDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsTransRepository extends JpaRepository<EventDetailsEntity, Long> {

    int countByOrganizerUUID (String userUUID);

    EventDetailsEntity findEventByEventUUID(String eventUUID);
}
