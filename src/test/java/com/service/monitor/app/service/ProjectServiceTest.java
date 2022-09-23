package com.service.monitor.app.service;

import com.service.monitor.app.domain.dto.DescriptionDto;
import com.service.monitor.app.domain.dto.ProjectMiniatureDto;
import com.service.monitor.app.domain.dto.crud.ProjectDto;
import com.service.monitor.app.domain.enums.ProjectType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void testSavingAndReadingProject(){

    }

    @Test
    public void sortingProjectsInDisplayOrder(){

        projectService.deleteAllProjects();

        ProjectDto project1 = new ProjectDto(ProjectType.NORMAL, "3", "", "", "", new DescriptionDto(), 3);
        ProjectDto project2 = new ProjectDto(ProjectType.NORMAL, "1", "", "", "", new DescriptionDto(), 1);
        ProjectDto project3 = new ProjectDto(ProjectType.NORMAL, "2", "", "", "", new DescriptionDto(), 2);

        projectService.saveAllProjects(Arrays.asList(project1, project2, project3));

        List<ProjectMiniatureDto> projectMiniatureDtos = projectService.getAllNormalDto();

        Assert.assertEquals("1",projectMiniatureDtos.get(0).getTitle());
        Assert.assertEquals("2",projectMiniatureDtos.get(1).getTitle());
        Assert.assertEquals("3",projectMiniatureDtos.get(2).getTitle());
    }
}