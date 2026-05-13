package com.frasato.user_service.infra.config;

import consortium.ConsortiumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public ManagedChannel managedChannel(){
        return ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    @Bean
    public ConsortiumServiceGrpc.ConsortiumServiceBlockingStub blockingStub(ManagedChannel managedChannel){
        return ConsortiumServiceGrpc.newBlockingStub(managedChannel);
    }
}