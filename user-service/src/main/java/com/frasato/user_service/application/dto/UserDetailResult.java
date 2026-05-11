package com.frasato.user_service.application.dto;

import com.frasato.user_service.domain.model.ConsortiumItem;

import java.util.List;

public record UserDetailResult(
    String name,
    String phone,
    String document,
    String address,
    List<ConsortiumItem> consortiums
){}