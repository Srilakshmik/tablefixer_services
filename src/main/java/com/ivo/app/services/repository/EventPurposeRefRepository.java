package com.ivo.app.services.repository;

import com.ivo.app.services.entity.EventPurposeRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPurposeRefRepository extends JpaRepository<EventPurposeRef, Integer> {

    List<EventPurposeRef> findAllByActiveTrueOrderByOrderBy();

}
