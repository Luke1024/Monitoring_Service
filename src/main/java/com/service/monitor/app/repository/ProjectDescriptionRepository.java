package com.service.monitor.app.repository;

import com.service.monitor.app.domain.ProjectDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDescriptionRepository extends CrudRepository<ProjectDescription, Long> {
}
