package com.sportygroup.bets.service.usecase;

import com.sportygroup.bets.persistence.BetEntity;
import com.sportygroup.bets.persistence.BetRepository;
import com.sportygroup.bets.service.domain.Bet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InteractorSettleBetUseCaseTest {

    @Mock
    private BetRepository betRepository;

    @InjectMocks
    private InteractorSettleBetUseCase useCase;

    @Test
    void settleBet() {
        var bet = Bet.of(UUID.randomUUID());
        var entity = buildBetEntity(bet);

        when(betRepository.findById(bet.getBetId()))
            .thenReturn(Optional.of(entity));

        when(betRepository.save(entity))
            .thenReturn(entity);

        assertThatCode(() -> useCase.settleBet(bet))
            .doesNotThrowAnyException();

        verify(betRepository).findById(bet.getBetId());
        verify(betRepository).save(entity.settled());
    }

    private BetEntity buildBetEntity(final Bet bet) {
        return new BetEntity(bet.getBetId(),
            BigDecimal.ONE,
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            UUID.randomUUID(),
            false);
    }
}
