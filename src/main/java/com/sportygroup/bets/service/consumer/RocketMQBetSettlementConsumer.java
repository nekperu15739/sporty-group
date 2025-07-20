package com.sportygroup.bets.service.consumer;

import com.sportygroup.bets.service.domain.Bet;
import com.sportygroup.bets.service.usecase.SettleBetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
    topic = "bet-settlements",
    consumerGroup = "bet-settlement-consumer-group"
)
public class RocketMQBetSettlementConsumer implements RocketMQListener<Bet> {

    private final SettleBetUseCase settleBetUseCase;

    public void onMessage(final Bet bet) {
        settleBetUseCase.settleBet(bet);
    }
}
