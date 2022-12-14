package com.example.authservice.Grpc;

import Grpc.Idea;
import Grpc.IdeaServiceGrpc;
import com.example.authservice.Exception.NotValidRequestException;
import com.example.authservice.Exception.UserNotFoundException;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class IdeaGrpcClient {
    @GrpcClient("idea-service")
    private IdeaServiceGrpc.IdeaServiceFutureStub futureStub;

    public String saveIdea(final String ideaJson){
        try {
            final ListenableFuture<Idea.IdeaSaveResponse> response = futureStub.saveIdea(
                    Idea.IdeaSaveRequest.newBuilder()
                            .setRequest(ideaJson)
                            .build());
            final Idea.IdeaSaveResponse res = response.get();
            switch (res.getStatus()){
                case (404):
                    throw new UserNotFoundException("User not found");
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
