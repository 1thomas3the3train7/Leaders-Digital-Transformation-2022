package com.example.ideaservice.GrpcClient;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.example.ideaservice.Exception.UserNotFoundException;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserGrpcClient {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceFutureStub futureStub;

    private final Gson gson = new Gson();
    public String getUserByEmail(final String email){
        try {
            final ListenableFuture<User.UserGetResponse> response = futureStub.getUserByEmail(
                    User.UserGetRequest.newBuilder()
                            .setEmail(email)
                            .build());
            final User.UserGetResponse res = response.get();

            switch (res.getStatus()){
                case (404):
                    throw new UserNotFoundException("User not found");
            }
            return res.getResponse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
