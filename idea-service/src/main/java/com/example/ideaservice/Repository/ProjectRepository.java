package com.example.ideaservice.Repository;

import com.example.ideaservice.Model.Project.ProjectBase;

public interface ProjectRepository {
    void save(ProjectBase projectBase);
    void delete(ProjectBase projectBase);
}
