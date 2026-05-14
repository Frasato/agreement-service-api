package com.frasato.consortium_service.infra.web.controller;

import com.frasato.consortium_service.application.usecase.CreateConsortiumUseCase;
import com.frasato.consortium_service.application.usecase.FindOneConsortiumUseCase;
import com.frasato.consortium_service.application.usecase.ListConsortiumsUseCase;
import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.infra.web.assembler.ConsortiumAssembler;
import com.frasato.consortium_service.infra.web.dto.RequestCreateConsortiumDto;
import com.frasato.consortium_service.infra.web.dto.ResponseAllConsortiumDto;
import com.frasato.consortium_service.infra.web.dto.ResponseConsortiumDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/consortium")
public class ConsortiumController {
    private final CreateConsortiumUseCase createConsortiumUseCase;
    private final FindOneConsortiumUseCase findOneConsortiumUseCase;
    private final ListConsortiumsUseCase listConsortiumsUseCase;
    private final ConsortiumAssembler consortiumAssembler;

    public ConsortiumController(CreateConsortiumUseCase createConsortiumUseCase, FindOneConsortiumUseCase findOneConsortiumUseCase, ListConsortiumsUseCase listConsortiumsUseCase, ConsortiumAssembler consortiumAssembler){
        this.createConsortiumUseCase = createConsortiumUseCase;
        this.findOneConsortiumUseCase = findOneConsortiumUseCase;
        this.listConsortiumsUseCase = listConsortiumsUseCase;
        this.consortiumAssembler = consortiumAssembler;
    }

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<EntityModel<ResponseAllConsortiumDto>>> allConsortiums(){
        List<Consortium> consortiums = listConsortiumsUseCase.listAllConsortiums();
        CollectionModel<EntityModel<ResponseAllConsortiumDto>> response = consortiumAssembler.allConsortiumAssembler(consortiums);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ResponseConsortiumDto>> oneConsortium(@PathVariable("id") String id){
        Consortium consortium = findOneConsortiumUseCase.findOne(id);
        EntityModel<ResponseConsortiumDto> response = consortiumAssembler.oneConsortiumAssembler(consortium);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<EntityModel<ResponseConsortiumDto>> createConsortium(@RequestBody RequestCreateConsortiumDto createConsortiumDto){
        Consortium consortium = createConsortiumUseCase.createNewConsortium(createConsortiumDto.name(), createConsortiumDto.description(), createConsortiumDto.price());
        EntityModel<ResponseConsortiumDto> responseConsortiumDto = consortiumAssembler.createConsortiumAssembler(consortium);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseConsortiumDto);
    }
}
