package com.example.authservice.Grpc;

import Grpc.User;
import Grpc.UserServiceGrpc;
import com.example.authservice.DTO.UserDTO;
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

    private void handleException(final String response){
        //TODO exc handle
    }
    public String getUserByEmail(final String email){
        try {
            final ListenableFuture<User.UserGetResponse> response = futureStub.getUserByEmail(
                    User.UserGetRequest.newBuilder()
                            .setEmail(email)
                            .build());

            return response.get().getResponse();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String userSave(final String request){
        try {
            final ListenableFuture<User.UserSaveResponse> response = futureStub.saveUser(
                    User.UserSaveRequest.newBuilder()
                            .setRequest(request)
                            .build());
            return response.get().getResponse();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "FAIL";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
}
