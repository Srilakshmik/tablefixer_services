package com.ivo.app.services.repository;

import com.ivo.app.services.entity.EventPayPrefRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPayPrefRepository extends JpaRepository<EventPayPrefRef, Integer> {

    List<EventPayPrefRef> findAllByActiveTrueOrderByOrderBy();

}
