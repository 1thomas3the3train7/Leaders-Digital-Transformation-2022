package com.example.ideaservice.GrpcServer;

import Grpc.Project;
import Grpc.ProjectServiceGrpc;
import com.example.ideaservice.Service.ProjectService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProjectGrpcServer extends ProjectServiceGrpc.ProjectServiceImplBase {
    private final ProjectService projectService;

    public ProjectGrpcServer(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void createProject(Project.ProjectCreateRequest request,
                              StreamObserver<Project.ProjectCreateResponse> responseObserver) {
        final Project.ProjectCreateResponse response = Project.ProjectCreateResponse.newBuilder()
                .setResponse(projectService.createProjectAndValid(request.getRequest()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
