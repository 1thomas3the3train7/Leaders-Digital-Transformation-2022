package com.example.ideaservice.GrpcClient;

import Grpc.Idea;
import Grpc.IdeaServiceGrpc;
import com.example.ideaservice.Exception.NotValidRequestException;
import com.example.ideaservice.Exception.UserNotFoundException;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class IdeaGrpcClient {
    @GrpcClient("user-service")
    private IdeaServiceGrpc.IdeaServiceFutureStub futureStub;

    public String updateIdeaToUserService(final String ideaJSON){

        try {
            final ListenableFuture<Idea.IdeaUpdateResponse> response = futureStub.ideaUpdate(
                    Idea.IdeaUpdateRequest.newBuilder()
                            .setRequest(ideaJSON)
                            .build());
            final Idea.IdeaUpdateResponse res;

            res = response.get();
            switch (res.getStatus()){
                case (404):
                    throw new UserNotFoundException();
                case (400):
                    throw new NotValidRequestException();
                default:
                    return res.getResponse();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
