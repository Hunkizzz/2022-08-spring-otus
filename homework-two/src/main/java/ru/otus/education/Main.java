package ru.otus.education;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.education.service.ApplicationService;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ApplicationService applicationService = (ApplicationService) context.getBean("applicationService");
        applicationService.run();
    }
}
