package com.sportygroup.bets.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface BetRepository extends JpaRepository<BetEntity, UUID> {

    Set<BetEntity> findBetEntitiesByEventIdAndEventWinnerId(UUID eventId, UUID winnerId);
}

