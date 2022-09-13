package com.service.monitor.app.repository;

import com.service.monitor.app.domain.UserSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<UserSession, Long> {
}
