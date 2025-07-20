package com.sportygroup.bets.service.consumer;

import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.service.usecase.CheckBetsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaSportEventConsumer {

    private final CheckBetsUseCase checkBetsUseCase;

    @KafkaListener(
        topics = "${kafka.topic.event-outcomes:event-outcomes}",
        groupId = "betting-group"
    )
    public void consume(final SportEventResource sportEvent) {
        log.debug("NewSportEvent consume:{}", sportEvent.getId());
        checkBetsUseCase.lookBetForWinners(sportEvent);
    }
}
