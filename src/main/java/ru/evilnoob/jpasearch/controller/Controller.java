package ru.evilnoob.jpasearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evilnoob.jpasearch.model.dto.UserResponseDto;
import ru.evilnoob.jpasearch.service.SearchServiceImpl;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final SearchServiceImpl service;

    @GetMapping(value = "/user/searchEm")
    public UserResponseDto searchEm() {
        return UserResponseDto.getUserResponseDto(service.searchEm());
    }

    @GetMapping(value = "/user/searchRep")
    public UserResponseDto searchRep() {
        return UserResponseDto.getUserResponseDto(service.searchRep());
    }

}
