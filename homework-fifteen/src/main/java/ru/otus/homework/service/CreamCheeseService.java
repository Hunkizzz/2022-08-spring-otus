package ru.otus.homework.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Onigiri;

import java.util.Random;
import java.util.UUID;

@Service
@Log4j2
public class CreamCheeseService {
    public Onigiri addCreamCheese(Onigiri onigiri) throws InterruptedException {
        log.info("Повар добавляет cream cheese");
        Thread.sleep(2000);
        onigiri.setCreamCheese(true);
        log.info("Повар добавил cream cheese");
        return onigiri;
    }
}