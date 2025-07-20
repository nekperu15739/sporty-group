package com.sportygroup.bets.service.usecase;

import com.sportygroup.bets.persistence.BetEntity;
import com.sportygroup.bets.persistence.BetRepository;
import com.sportygroup.bets.service.domain.Bet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InteractorSettleBetUseCase implements SettleBetUseCase {

    private final BetRepository betRepository;

    @Override
    @Transactional
    public void settleBet(final Bet bet) {
        log.info("Settling bet for id:{}", bet.getBetId());

        betRepository.findById(bet.getBetId())
            .map(BetEntity::settled)
            .map(betRepository::save);
    }
}
