package com.frasato.consortium_service.infra.web.assembler;

import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.infra.web.controller.ConsortiumController;
import com.frasato.consortium_service.infra.web.dto.ResponseAllConsortiumDto;
import com.frasato.consortium_service.infra.web.dto.ResponseConsortiumDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConsortiumAssembler {

    public EntityModel<ResponseConsortiumDto> createConsortiumAssembler(Consortium consortium){
        ResponseConsortiumDto response = new ResponseConsortiumDto(
                consortium.getId(),
                consortium.getName(),
                consortium.getDescription(),
                consortium.getPrice(),
                consortium.getActive()
        );

        return EntityModel.of(
                response,
                linkTo(
                        methodOn(ConsortiumController.class)
                                .oneConsortium(consortium.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(ConsortiumController.class)
                                .allConsortiums()
                ).withRel("all-consortiums")
        );
    }

    public CollectionModel<EntityModel<ResponseAllConsortiumDto>> allConsortiumAssembler(List<Consortium> consortiums){
        List<EntityModel<ResponseAllConsortiumDto>> response = consortiums.stream()
                        .map(consortium -> {

                            ResponseAllConsortiumDto dto = ResponseAllConsortiumDto.fromEntity(consortium);

                            return EntityModel.of(
                                    dto,

                                    linkTo(
                                            methodOn(ConsortiumController.class)
                                                    .oneConsortium(consortium.getId())
                                    ).withSelfRel()
                            );
                        })
                        .toList();

        return CollectionModel.of(
                response,

                linkTo(
                        methodOn(ConsortiumController.class)
                                .allConsortiums()
                ).withSelfRel()
        );
    }

    public EntityModel<ResponseConsortiumDto> oneConsortiumAssembler(Consortium consortium){
        ResponseConsortiumDto response = new ResponseConsortiumDto(
                consortium.getId(),
                consortium.getName(),
                consortium.getDescription(),
                consortium.getPrice(),
                consortium.getActive()
        );

        return EntityModel.of(
                response,
                linkTo(
                        methodOn(ConsortiumController.class)
                                .oneConsortium(consortium.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(ConsortiumController.class)
                                .allConsortiums()
                ).withRel("all-consortiums")
        );
    }
}
