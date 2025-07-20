package com.sportygroup.bets.service.usecase;

import com.github.javafaker.Faker;
import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.persistence.BetEntity;
import com.sportygroup.bets.persistence.BetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InteractorCheckBetsUseCaseTest {

    private static final Faker faker = new Faker();

    @Mock
    private BetRepository repository;

    @InjectMocks
    private InteractorCheckBetsUseCase useCase;

    @Test
    void lookBetForWinners() {
        var eventResource = buildResource();
        var betEntity = mock(BetEntity.class);

        when(repository.findBetEntitiesByEventIdAndEventWinnerId(eventResource.getId(), eventResource.getWinnerId()))
                .thenReturn(Set.of(betEntity));

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
