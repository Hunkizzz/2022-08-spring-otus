package ru.otus.homework.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.homework.service.CreamCheeseService;
import ru.otus.homework.service.FillingService;
import ru.otus.homework.service.FirstRiceLayerService;
import ru.otus.homework.service.TopRiceLayerService;

@Configuration
@RequiredArgsConstructor
public class OnigiriCreationFlowConfig {
    private final CreamCheeseService creamCheeseService;
    private final FillingService fillingService;
    private final FirstRiceLayerService firstRiceLayerService;
    private final TopRiceLayerService topRiceLayerService;

    @Bean
    public QueueChannel inputChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel outputChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow workFlow() {
        return IntegrationFlows.from("inputChannel")
                .handle(firstRiceLayerService, "addFirstRiceLayer")
                .handle(creamCheeseService, "addCreamCheese")
                .handle(fillingService, "addFilling")
                .handle(topRiceLayerService, "addTopRiceLayer")
                .channel("outputChannel")
                .get();
    }
}
