package com.frasato.user_service.domain.repository;

import com.frasato.user_service.domain.model.ConsortiumItem;

import java.util.List;

public interface ConsortiumIntegrationRepository {
    List<ConsortiumItem> fetchConsortiumDetails(List<String> ids);
}
