package com.ivo.app.services.repository;

import com.ivo.app.services.entity.GenderCatgRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventGenderPrefRepository extends JpaRepository<GenderCatgRef, Integer> {

    List<GenderCatgRef> findAllByActiveGenderTypeTrueOrderByOrderBy();

    List<GenderCatgRef> findAllByActiveEventTypeTrueOrderByOrderBy();

}
