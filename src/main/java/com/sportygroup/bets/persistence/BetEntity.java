package com.sportygroup.bets.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
@Table(name = "bets")
public class BetEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1041811642909433776L;

    @Id
    private UUID betId;
    private BigDecimal betAmount;
    private UUID eventId;
    private UUID eventMarketId;
    private UUID eventWinnerId;
    private UUID userId;
    private boolean isSettled;

    protected BetEntity() {
    }

    public BetEntity(final UUID betId, final BigDecimal betAmount, final UUID eventId, final UUID eventMarketId,
                     final UUID eventWinnerId, final UUID userId, final boolean isSettled) {
        this.betId = betId;
        this.betAmount = betAmount;
        this.eventId = eventId;
        this.eventMarketId = eventMarketId;
        this.eventWinnerId = eventWinnerId;
        this.userId = userId;
        this.isSettled = isSettled;
    }

    public BetEntity settled() {
        this.isSettled = true;
        return this;
    }
}
