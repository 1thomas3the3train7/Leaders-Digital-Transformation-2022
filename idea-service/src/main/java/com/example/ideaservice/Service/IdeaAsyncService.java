package com.example.ideaservice.Service;

import com.example.ideaservice.GrpcClient.IdeaGrpcClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class IdeaAsyncService {
    private final IdeaGrpcClient ideaGrpcClient;

    public IdeaAsyncService(IdeaGrpcClient ideaGrpcClient) {
        this.ideaGrpcClient = ideaGrpcClient;
    }

    @Async
    public void updateIdeaToUserService(final String ideaJSON){
        ideaGrpcClient.updateIdeaToUserService(ideaJSON);
    }
}
