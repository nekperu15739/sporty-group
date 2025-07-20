package com.sportygroup.bets.service.usecase;

import com.sportygroup.bets.api.SportEventResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.sportygroup.bets.config.TopicConstant.EVENT_OUTCOMES;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSportEventAddUseCase implements SportEventAddUseCase {

    private final KafkaTemplate<String, SportEventResource> template;

    public void addNewSportEvent(final SportEventResource eventToSent) {
        template.send(EVENT_OUTCOMES, eventToSent);
        log.debug("NewSportEvent sent:{}", eventToSent.getId());
    }
}
