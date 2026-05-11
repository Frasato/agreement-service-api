package com.frasato.user_service.infra.gateway;

import com.frasato.user_service.domain.model.ConsortiumItem;
import com.frasato.user_service.domain.repository.ConsortiumIntegrationRepository;
import consortium.ConsortiumServiceGrpc;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ConsortiumGrpcGateway implements ConsortiumIntegrationRepository {

    private final ConsortiumServiceGrpc.ConsortiumServiceBlockingStub stub;

    public ConsortiumGrpcGateway(ConsortiumServiceGrpc.ConsortiumServiceBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public List<ConsortiumItem> fetchConsortiumDetails(List<String> ids) {
        var request = consortium.ConsortiumClient.Request.newBuilder()
                .addAllIds(ids)
                .build();

        var response = stub.searchConsortium(request);

        return response.getItemsList().stream()
                .map(item -> new ConsortiumItem(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice()))
                .toList();
    }
}