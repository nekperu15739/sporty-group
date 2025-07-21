package com.sportygroup.bets;

import com.github.javafaker.Faker;
import com.sportygroup.bets.api.SportEventResource;
import com.sportygroup.bets.config.KafkaConfig;
import com.sportygroup.bets.service.consumer.KafkaSportEventConsumer;
import com.sportygroup.bets.service.usecase.CheckBetsUseCase;
import com.sportygroup.bets.service.usecase.KafkaSportEventAddUseCase;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest(classes = {
    KafkaConfig.class,
    KafkaSportEventAddUseCase.class,
    KafkaSportEventConsumer.class
})
@Testcontainers
@ExtendWith(OutputCaptureExtension.class)
@ImportAutoConfiguration(KafkaAutoConfiguration.class)
class KafkaProducerConsumerIT {

    private static final Faker faker = new Faker();

    @Container
    static final KafkaContainer kafka = new KafkaContainer(
        DockerImageName.parse("confluentinc/cp-kafka:7.6.1")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaSportEventAddUseCase kafkaSportEventAddUseCase;

    @MockitoBean
    private CheckBetsUseCase checkBetsUseCase;

    @Test
    void addNewSportEvent(final CapturedOutput output) {
        var eventToSent = buildResource();

        assertThatCode(() -> kafkaSportEventAddUseCase.addNewSportEvent(eventToSent))
            .doesNotThrowAnyException();

        Awaitility.await()
            .pollInterval(Duration.ofSeconds(3))
            .atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> assertThat(output).contains("NewSportEvent consume"));
    }

    private SportEventResource buildResource() {
        return SportEventResource
            .builder()
            .name(faker.name().name())
            .id(UUID.randomUUID())
            .winnerId(UUID.randomUUID())
            .build();
    }
}
