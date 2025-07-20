package com.sportygroup.bets.api;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class SportEventResource {

    private final UUID id;
    private final String name;
    private final UUID winnerId;
}
