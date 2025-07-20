package com.sportygroup.bets.service.usecase;

import com.sportygroup.bets.api.SportEventResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static com.sportygroup.bets.config.TopicConstant.EVENT_OUTCOMES;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaSportEventAddUseCaseTest {

    @Mock
    private KafkaTemplate<String, SportEventResource> template;

    @InjectMocks
    private KafkaSportEventAddUseCase kafkaSportEventAddUseCase;

    @Test
    void addNewSportEvent() {
        var eventToSent = Mockito.mock(SportEventResource.class);

        kafkaSportEventAddUseCase.addNewSportEvent(eventToSent);

        verify(template).send(eq(EVENT_OUTCOMES), eq(eventToSent));
    }
}
