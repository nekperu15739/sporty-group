package com.sportygroup.bets.service.usecase;

import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.persistence.BetRepository;
import com.sportygroup.bets.service.domain.Bet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import static com.sportygroup.bets.config.TopicConstant.BET_SETTLEMENTS_TOPIC;


@Slf4j
@Service
@RequiredArgsConstructor
public class InteractorCheckBetsUseCase implements CheckBetsUseCase {

    private final BetRepository repository;
    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public void lookBetForWinners(final SportEventResource sportEvent) {
        log.info("looking for winners {}", sportEvent.getId());
        var winners = repository.findBetEntitiesByEventIdAndEventWinnerId(sportEvent.getId(), sportEvent.getWinnerId());

        winners.forEach(entity -> rocketMQTemplate.convertAndSend(BET_SETTLEMENTS_TOPIC, Bet.of(entity.getBetId())));
    }
}
