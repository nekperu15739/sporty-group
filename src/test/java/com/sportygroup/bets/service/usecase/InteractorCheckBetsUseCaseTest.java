package com.sportygroup.bets.service.usecase;

import com.github.javafaker.Faker;
import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.persistence.BetEntity;
import com.sportygroup.bets.persistence.BetRepository;
import com.sportygroup.bets.service.domain.Bet;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static com.sportygroup.bets.config.TopicConstant.BET_SETTLEMENTS_TOPIC;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InteractorCheckBetsUseCaseTest {

    private static final Faker faker = new Faker();

    @Mock
    private BetRepository repository;

    @Mock
    private RocketMQTemplate rocketMQTemplate;

    @InjectMocks
    private InteractorCheckBetsUseCase useCase;

    @Test
    void lookBetForWinners() {
        var eventResource = buildResource();
        var betEntity = mock(BetEntity.class);

        when(repository.findBetEntitiesByEventIdAndEventWinnerId(eventResource.getId(), eventResource.getWinnerId()))
                .thenReturn(Set.of(betEntity));

        doNothing().when(rocketMQTemplate).convertAndSend(eq(BET_SETTLEMENTS_TOPIC), any(Bet.class));

        useCase.lookBetForWinners(eventResource);

        verify(repository).findBetEntitiesByEventIdAndEventWinnerId(eventResource.getId(), eventResource.getWinnerId());
    }

    private SportEventResource buildResource() {
        return SportEventResource
            .builder()
            .name(faker.name().name())
            .id(UUID.randomUUID())
            .winnerId(UUID.randomUUID())
            .build();
    }
}
