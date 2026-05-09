package com.frasato.consortium_service.infra.web.controller;

import com.frasato.consortium_service.application.usecase.CreateConsortiumUseCase;
import com.frasato.consortium_service.application.usecase.FindOneConsortiumUseCase;
import com.frasato.consortium_service.application.usecase.ListConsortiumsUseCase;
import com.frasato.consortium_service.domain.model.Consortium;
import com.frasato.consortium_service.infra.web.dto.RequestCreateConsortiumDto;
import com.frasato.consortium_service.infra.web.dto.ResponseAllConsortiumDto;
import com.frasato.consortium_service.infra.web.dto.ResponseConsortiumDto;
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

    public ConsortiumController(CreateConsortiumUseCase createConsortiumUseCase, FindOneConsortiumUseCase findOneConsortiumUseCase, ListConsortiumsUseCase listConsortiumsUseCase){
        this.createConsortiumUseCase = createConsortiumUseCase;
        this.findOneConsortiumUseCase = findOneConsortiumUseCase;
        this.listConsortiumsUseCase = listConsortiumsUseCase;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseAllConsortiumDto>> allConsortiums(){

        List<ResponseAllConsortiumDto> response =
                listConsortiumsUseCase.listAllConsortiums()
                        .stream()
                        .map(ResponseAllConsortiumDto::fromEntity)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseConsortiumDto> oneConsortium(@PathVariable("id") String id){
        Consortium consortium = findOneConsortiumUseCase.findOne(id);
        ResponseConsortiumDto dto = new ResponseConsortiumDto(
                consortium.getId(),
                consortium.getName(),
                consortium.getDescription(),
                consortium.getPrice(),
                consortium.getActive()
        );

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseConsortiumDto> createConsortium(@RequestBody RequestCreateConsortiumDto createConsortiumDto){
        Consortium consortium = createConsortiumUseCase.createNewConsortium(createConsortiumDto.name(), createConsortiumDto.description(), createConsortiumDto.price());

        ResponseConsortiumDto responseConsortiumDto = new ResponseConsortiumDto(
                consortium.getId(),
                consortium.getName(),
                consortium.getDescription(),
                consortium.getPrice(),
                consortium.getActive()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseConsortiumDto);
    }
}
