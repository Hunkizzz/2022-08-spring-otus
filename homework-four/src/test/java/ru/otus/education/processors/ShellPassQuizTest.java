package ru.otus.education.processors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.education.config.MessageSourceConfig;
import ru.otus.education.model.User;
import ru.otus.education.service.CsvService;
import ru.otus.education.service.CsvServiceImpl;
import ru.otus.education.service.InternationalService;
import ru.otus.education.service.QuizService;
import ru.otus.education.service.QuizServiceImpl;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.processors.QuizProcessor;
import ru.otus.education.service.shell.ShellQuizServiceImpl;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Процессор команды внесения данных пользователя")
@SpringBootTest(classes = {MessageSourceConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShellPassQuizTest {
    CsvService csvService;

    QuizProcessor quizProcessor;

    QuizService quizService;

    private MessageSource messageSource;

    @Autowired
    public void setInternationalService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @BeforeAll
    public void init() throws NoSuchFieldException, IllegalAccessException {
        csvService = new CsvServiceImpl();
        Field f = csvService.getClass().getDeclaredField("csvResourcePath"); //NoSuchFieldException
        f.setAccessible(true);
        f.set(csvService, "/csv/questions.csv");
        InternationalService internationalService = new InternationalService(Locale.ENGLISH, messageSource);
        quizService = new ShellQuizServiceImpl(internationalService, csvService);
        quizProcessor = new QuizProcessor(new MenuOption(1, "asd"), quizService);
    }

    @DisplayName("должен корректно проходить тестирование")
    @Test
    void shouldCorrectPassTheTest() throws NoSuchFieldException, IllegalAccessException {
        quizService.setUser("test", "test");
        var expectedFirstAnswer = "1\n";
        var expectedSecondAnswer = "3\n";
        var expectedThirdAnswer = "2\n";
        var expectedFourthAnswer = "1\n";
        var expectedFifthAnswer = "3\n";
        String finalString = expectedFirstAnswer +
                expectedSecondAnswer +
                expectedThirdAnswer +
                expectedFourthAnswer +
                expectedFifthAnswer;

        ByteArrayInputStream in = new ByteArrayInputStream(finalString.getBytes());
        System.setIn(in);
        quizProcessor.processCommand();
        assertThat(quizService.getResult().isPassed()).isTrue();
        assertThat(quizService.getResult().isDone()).isTrue();
        assertThat(quizService.getResult().getRightAnswers()).isEqualTo(5);
    }
}
