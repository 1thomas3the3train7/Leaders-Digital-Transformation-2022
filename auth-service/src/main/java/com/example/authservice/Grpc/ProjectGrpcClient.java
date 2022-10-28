package com.example.authservice.Grpc;

import Grpc.Project;
import Grpc.ProjectServiceGrpc;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProjectGrpcClient {
    @GrpcClient("idea-service")
    private ProjectServiceGrpc.ProjectServiceFutureStub futureStub;
    public String createProject(final String projectJSON){
        try {
            final ListenableFuture<Project.ProjectCreateResponse> response = futureStub.createProject(
                    Project.ProjectCreateRequest.newBuilder()
                            .setRequest(projectJSON)
                            .build());

            final Project.ProjectCreateResponse res = response.get();

            return res.getResponse();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
