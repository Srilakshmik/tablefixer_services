package com.ivo.app.services.repository;

import com.ivo.app.services.entity.UserInfoRef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRefRepository extends CrudRepository<UserInfoRef, Long > {

    UserInfoRef findByuserUUID(String userUUID);
}
