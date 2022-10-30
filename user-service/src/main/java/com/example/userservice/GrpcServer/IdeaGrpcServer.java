package com.example.userservice.GrpcServer;

import Grpc.Idea;
import Grpc.IdeaServiceGrpc;
import com.example.userservice.Exception.NotValidRequestException;
import com.example.userservice.Exception.UserNotFoundException;
import com.example.userservice.Service.IdeaService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class IdeaGrpcServer extends IdeaServiceGrpc.IdeaServiceImplBase {
    private final IdeaService ideaService;

    public IdeaGrpcServer(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @Override
    public void ideaUpdate(Idea.IdeaUpdateRequest request,
                           StreamObserver<Idea.IdeaUpdateResponse> responseObserver) {
        try {
            final Idea.IdeaUpdateResponse response = Idea.IdeaUpdateResponse.newBuilder()
                    .setResponse(ideaService.updateIdeaAndValid(request.getRequest()))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (UserNotFoundException u){
            final Idea.IdeaUpdateResponse response = Idea.IdeaUpdateResponse.newBuilder()
                    .setStatus(404)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (NotValidRequestException e){
            final Idea.IdeaUpdateResponse response = Idea.IdeaUpdateResponse.newBuilder()
                    .setStatus(400)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
