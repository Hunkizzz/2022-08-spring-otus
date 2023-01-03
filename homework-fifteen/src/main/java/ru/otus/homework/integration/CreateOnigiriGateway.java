package ru.otus.homework.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.domain.Onigiri;

@MessagingGateway
public interface CreateOnigiriGateway {

    @Gateway(requestChannel = "inputChannel", replyChannel = "outputChannel")
    Onigiri process(Onigiri onigiri);
}
