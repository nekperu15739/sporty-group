package com.sportygroup.bets.service.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class Bet {

    private final UUID betId;

    public static Bet of(final UUID betId) {
        return Bet.builder().betId(betId).build();
    }
}
