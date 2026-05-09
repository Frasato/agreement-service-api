package com.frasato.consortium_service.infra.web.dto;

import com.frasato.consortium_service.domain.model.Consortium;

public record ResponseAllConsortiumDto(
        String id,
        String name,
        String description,
        Integer price,
        Boolean active
) {

    public static ResponseAllConsortiumDto fromEntity(Consortium consortium){
        return new ResponseAllConsortiumDto(
                consortium.getId(),
                consortium.getName(),
                consortium.getDescription(),
                consortium.getPrice(),
                consortium.getActive()
        );
    }
}