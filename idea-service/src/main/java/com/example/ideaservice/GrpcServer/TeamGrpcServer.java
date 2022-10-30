package com.example.ideaservice.GrpcServer;

import Grpc.Team;
import Grpc.TeamServiceGrpc;
import com.example.ideaservice.Exception.NotValidRequestException;
import com.example.ideaservice.Exception.UserNotFoundException;
import com.example.ideaservice.Service.TeamService;
import io.grpc.stub.StreamObserver;

public class TeamGrpcServer extends TeamServiceGrpc.TeamServiceImplBase {
    private final TeamService teamService;

    public TeamGrpcServer(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void createTeam(Team.TeamCreateRequest request, StreamObserver<Team.TeamCreateResponse> responseObserver) {
        try {
            final Team.TeamCreateResponse response = Team.TeamCreateResponse.newBuilder()
                    .setResponse(teamService.createTeamAndValid(request.getRequest()))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (UserNotFoundException n){
            final Team.TeamCreateResponse response = Team.TeamCreateResponse.newBuilder()
                    .setStatus(404)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (NotValidRequestException e){
            final Team.TeamCreateResponse response = Team.TeamCreateResponse.newBuilder()
                    .setStatus(400)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
