package com.sportygroup.bets.service.consumer;

import com.sportygroup.bets.service.domain.Bet;
import com.sportygroup.bets.service.usecase.SettleBetUseCase;
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
class RocketMQBetSettlementConsumerTest {

    @Mock
    private SettleBetUseCase settleBetUseCase;

    @InjectMocks
    private RocketMQBetSettlementConsumer consumer;

    @Test
    void onMessage() {
        var bet = mock(Bet.class);

        doNothing().when(settleBetUseCase).settleBet(bet);

        assertThatCode(() -> consumer.onMessage(bet))
            .doesNotThrowAnyException();


        verify(settleBetUseCase).settleBet(bet);
    }
}
