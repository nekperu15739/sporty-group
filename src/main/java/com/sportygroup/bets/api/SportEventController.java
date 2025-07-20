package com.sportygroup.bets.api;

import com.sportygroup.bets.service.usecase.SportEventAddUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.accepted;

@Slf4j
@RestController
@RequestMapping("/sport-event")
@RequiredArgsConstructor
public class SportEventController {

    private final SportEventAddUseCase addUseCase;

    @PostMapping
    public ResponseEntity<SportEventResource> addNewSportEvent(@RequestBody final SportEventResource sportEventToAdd) {
        log.debug("request to addNewSportEvent {}", sportEventToAdd.getId());

        addUseCase.addNewSportEvent(sportEventToAdd);

        return accepted()
            .body(sportEventToAdd);
    }
}
