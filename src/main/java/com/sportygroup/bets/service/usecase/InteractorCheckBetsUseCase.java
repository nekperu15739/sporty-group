package com.sportygroup.bets.service.usecase;

import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.persistence.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class InteractorCheckBetsUseCase implements CheckBetsUseCase {

    private final BetRepository repository;

    @Override
    public void lookBetForWinners(final SportEventResource sportEvent) {
        log.info("looking for winners {}", sportEvent.getId());
        var winners = repository.findBetEntitiesByEventIdAndEventWinnerId(sportEvent.getId(), sportEvent.getWinnerId());

        winners.forEach(betEntity -> log.info("bet:{}", betEntity));
    }
}
