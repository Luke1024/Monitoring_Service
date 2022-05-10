package com.service.monitor.app.repository;


import com.service.monitor.app.domain.UserSession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<UserSession, Long> {

}
