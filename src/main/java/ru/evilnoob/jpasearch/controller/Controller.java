package ru.evilnoob.jpasearch.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.evilnoob.jpasearch.model.entity.User;
import ru.evilnoob.jpasearch.service.SearchServiceImpl;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final SearchServiceImpl service;

    @GetMapping(value = "/user/searchEm")
    public List<User> searchEm() {
        return service.searchEm();
    }

    @GetMapping(value = "/user/searchRep")
    public List<User> searchRep() {
        return service.searchRep();
    }

}
