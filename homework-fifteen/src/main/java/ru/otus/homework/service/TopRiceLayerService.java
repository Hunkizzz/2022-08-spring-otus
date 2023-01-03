package ru.otus.homework.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Onigiri;

import java.util.Random;
import java.util.UUID;

@Service
@Log4j2
public class TopRiceLayerService {
    public Onigiri addTopRiceLayer(Onigiri onigiri) throws InterruptedException {
        log.info("Повар добавляет верхний слой риса");
        Thread.sleep(2000);
        onigiri.setTopRiceLayer(true);
        log.info("Повар добавил верхний слой риса");
        return onigiri;
    }
}
