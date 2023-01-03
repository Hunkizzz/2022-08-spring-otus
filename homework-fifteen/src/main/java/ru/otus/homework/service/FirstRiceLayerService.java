package ru.otus.homework.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Onigiri;

@Service
@Log4j2
public class FirstRiceLayerService {

    public Onigiri addFirstRiceLayer(Onigiri onigiri) throws InterruptedException {
        log.info("Повар добавляет нижний слой риса");
        Thread.sleep(2000);
        onigiri.setBottomRiceLayer(true);
        log.info("Повар добавил нижний слой риса");
        return onigiri;
    }
}
