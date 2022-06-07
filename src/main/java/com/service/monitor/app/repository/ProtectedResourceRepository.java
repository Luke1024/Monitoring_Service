package com.service.monitor.app.repository;

import com.service.monitor.app.domain.ProtectedResource;
import org.springframework.data.repository.CrudRepository;

public interface ProtectedResourceRepository extends CrudRepository<ProtectedResource, Long> {
}
