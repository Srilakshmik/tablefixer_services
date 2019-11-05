package com.ivo.app.services.repository;

import com.ivo.app.services.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisinesRepository extends JpaRepository<Cuisine, Integer> {

    List<Cuisine> findAllBylocTypeIdAndActiveTrue(Integer locTypeId);

}
