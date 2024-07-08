package ru.evilnoob.jpasearch.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.evilnoob.jpasearch.model.entity.User;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl {

    private final CriteriaBuildingServiceImpl criteriaBuildingService;

    public List<User> searchEm() {
        return criteriaBuildingService.searchEm();
    }

    public List<User> searchRep() {
        return criteriaBuildingService.searchRep();
    }

}
