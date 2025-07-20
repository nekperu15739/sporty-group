package com.sportygroup.bets.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BetRepositoryTest {

    @Autowired
    private BetRepository betRepository;

    @Test
    void findBetEntitiesByEventIdAndEventWinnerId() {
        var entities = betRepository.findBetEntitiesByEventIdAndEventWinnerId(
            UUID.fromString("14cb4507-acf3-4ce4-8a77-7c16a75cd108"),
            UUID.fromString("afac9201-4bcf-45aa-95c4-75740458ba53")
        );

        assertThat(entities)
            .isNotNull()
            .isNotEmpty()
            .hasSize(2);
    }
}
