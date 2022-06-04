package com.service.monitor.app.repository;

import com.service.monitor.app.domain.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByToken(String token);
}
