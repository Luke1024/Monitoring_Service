package com.service.monitor.app.repository;

import com.service.monitor.app.domain.AuthKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthKeyRepository extends CrudRepository<AuthKey, Long> {
}
