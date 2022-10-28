package com.example.userservice.GrpcServer;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserGrpcServer extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;

    public UserGrpcServer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getUserByEmail(User.UserGetRequest request, StreamObserver<User.UserGetResponse> responseObserver) {
        try {
            final User.UserGetResponse response = User.UserGetResponse.newBuilder()
                    .setResponse(userService.getUserByEmail(request.getEmail())).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            final User.UserGetResponse response = User.UserGetResponse.newBuilder()
                    .setStatus(404)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void saveUser(User.UserSaveRequest request, StreamObserver<User.UserSaveResponse> responseObserver) {
        final User.UserSaveResponse response = User.UserSaveResponse.newBuilder()
                .setResponse(userService.registerAndValidUser(request.getRequest())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
