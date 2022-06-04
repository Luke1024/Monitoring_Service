package com.service.monitor.app.repository;

import com.service.monitor.app.domain.Project;
import com.service.monitor.app.domain.enums.ProjectType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findByType(ProjectType type);
}
