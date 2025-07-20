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
    private static final long serialVersionUID = 3129552279180365225L;

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

    public BetEntity settled() {
        this.isSettled = true;
        return this;
    }
}
