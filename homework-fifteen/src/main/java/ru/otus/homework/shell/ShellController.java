package ru.otus.homework.shell;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.domain.Onigiri;
import ru.otus.homework.integration.CreateOnigiriGateway;

import java.util.concurrent.atomic.AtomicInteger;

@ShellComponent
@AllArgsConstructor
@Log4j2
public class ShellController {
    private static final AtomicInteger num = new AtomicInteger(1);
    private final CreateOnigiriGateway createOnigiriGateway;

    @ShellMethod("start")
    public void start() {
        Thread thread = new Thread(() -> {
            Onigiri onigiri = new Onigiri();
            log.info("В системе обнаружен новый заказ №" + num.getAndIncrement());
            onigiri = createOnigiriGateway.process(onigiri);
            log.info("Закрыл заявку: " + onigiri.toString());
        });
        thread.start();
    }
}
