package com.example.ideaservice.GrpcServer;

import Grpc.Idea;
import Grpc.IdeaServiceGrpc;
import com.example.ideaservice.Service.IdeaService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class IdeaGrpcServer extends IdeaServiceGrpc.IdeaServiceImplBase {
    private final IdeaService ideaService;

    public IdeaGrpcServer(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @Override
    public void saveIdea(Idea.IdeaSaveRequest request, StreamObserver<Idea.IdeaSaveResponse> responseObserver) {
        final Idea.IdeaSaveResponse response = Idea.IdeaSaveResponse.newBuilder()
                .setResponse(ideaService.saveIdeaAndValid(request.getRequest()))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
