package com.sportygroup.bets.service.usecase;


import com.sportygroup.bets.api.SportEventResource;

public interface CheckBetsUseCase {

    void lookBetForWinners(SportEventResource sportEvent);
}
