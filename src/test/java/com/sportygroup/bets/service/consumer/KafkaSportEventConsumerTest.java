package com.sportygroup.bets.service.consumer;

import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.service.usecase.CheckBetsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaSportEventConsumerTest {

    @Mock
    private CheckBetsUseCase checkBetsUseCase;

    @InjectMocks
    private KafkaSportEventConsumer kafkaSportEventConsumer;

    @Test
    void consume() {
        var sportEvent = mock(SportEventResource.class);

        doNothing().when(checkBetsUseCase).lookBetForWinners(sportEvent);

        assertThatCode(() -> kafkaSportEventConsumer.consume(sportEvent))
            .doesNotThrowAnyException();

        verify(checkBetsUseCase).lookBetForWinners(sportEvent);
    }
}
