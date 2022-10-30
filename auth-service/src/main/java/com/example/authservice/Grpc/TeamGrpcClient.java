package com.example.authservice.Grpc;

import Grpc.Team;
import Grpc.TeamServiceGrpc;
import com.example.authservice.Exception.NotValidRequestException;
import com.example.authservice.Exception.UserNotFoundException;
import com.google.common.util.concurrent.ListenableFuture;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.util.concurrent.ExecutionException;

public class TeamGrpcClient {
    @GrpcClient("idea-service")
    private TeamServiceGrpc.TeamServiceFutureStub futureStub;
    public String createTeam(final String teamJSON){
        try {
            final ListenableFuture<Team.TeamCreateResponse> response = futureStub.createTeam(
                    Team.TeamCreateRequest.newBuilder()
                            .setRequest(teamJSON)
                            .build());
            final Team.TeamCreateResponse res = response.get();

            switch (res.getStatus()){
                case (404):
                    throw new UserNotFoundException();
                case (400):
                    throw new NotValidRequestException();
                default:
                    return res.getResponse();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
