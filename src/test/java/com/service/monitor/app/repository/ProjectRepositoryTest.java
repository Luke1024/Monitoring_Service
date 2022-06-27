package com.service.monitor.app.repository;

import com.service.monitor.app.domain.Project;
import com.service.monitor.app.domain.enums.ProjectType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void getProjectByType(){
        Project projectTypeNormal = new Project(ProjectType.NORMAL,"","","","","", 0);
        Project projectTypeMini = new Project(ProjectType.MINI, "", "", "", "", "", 0);

        projectRepository.save(projectTypeNormal);
        projectRepository.save(projectTypeMini);

        long normalId = projectTypeNormal.getId();


        List<Project> projects = projectRepository.findByType(ProjectType.NORMAL);

        Assert.assertTrue(normalOnly(projects));
    }

    private boolean normalOnly(List<Project> projects){
        for(Project project : projects) {
            if(project.getType().toString().equals(ProjectType.MINI.toString())){
                return false;
            }
        }
        return true;
    }
}