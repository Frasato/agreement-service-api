package com.frasato.consortium_service.infra.grpc;

import com.frasato.consortium_service.application.usecase.ListConsortiumsUseCase;
import consortium.Consortium;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class ConsortiumGrpcService extends consortium.ConsortiumServiceGrpc.ConsortiumServiceImplBase {

    private final ListConsortiumsUseCase listConsortiumsUseCase;

    public ConsortiumGrpcService(ListConsortiumsUseCase listConsortiumsUseCase) {
        this.listConsortiumsUseCase = listConsortiumsUseCase;
    }

    @Override
    public void searchConsortium(Consortium.Request request, StreamObserver<Consortium.Response> responseObserver) {
        var domains = listConsortiumsUseCase.listAllConsortiums();

        List<Consortium.ConsortiumItem> protoItems = domains.stream()
                .map(d -> Consortium.ConsortiumItem.newBuilder()
                        .setId(d.getId())
                        .setName(d.getName())
                        .setDescription(d.getDescription())
                        .setPrice(d.getPrice().longValue())
                        .build())
                .collect(Collectors.toList());

        Consortium.Response response = Consortium.Response.newBuilder()
                .addAllItems(protoItems)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}