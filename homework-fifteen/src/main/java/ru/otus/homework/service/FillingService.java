package ru.otus.homework.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Onigiri;

import java.util.Random;

@Service
@Log4j2
public class FillingService {
    private static final String[] FILLINGS = {"salmon", "mussels", "trout", "shrimps"};

    public Onigiri addFilling(Onigiri onigiri) throws InterruptedException {
        log.info("Повар добавляет начинку");
        Thread.sleep(2000);
        onigiri.setFilling(FILLINGS[new Random().nextInt(3)]);
        log.info("Повар добавил начинку");
        return onigiri;
    }
}
