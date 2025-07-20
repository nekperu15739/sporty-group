package com.sportygroup.bets.service.usecase;


import com.sportygroup.bets.service.domain.Bet;

public interface SettleBetUseCase {

    void settleBet(Bet bet);
}
