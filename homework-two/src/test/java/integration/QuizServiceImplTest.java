package integration;

import config.CsvConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.otus.education.config.AppConfig;
import ru.otus.education.config.MenuOptionCollectionConfig;
import ru.otus.education.model.CsvTransfer;
import ru.otus.education.model.ResultQuiz;
import ru.otus.education.model.User;
import ru.otus.education.service.ApplicationStopService;
import ru.otus.education.service.ApplicationStopServiceImpl;
import ru.otus.education.service.QuizService;
import ru.otus.education.service.QuizServiceImpl;
import ru.otus.education.service.menu.MenuOptionsRegistryImpl;
import ru.otus.education.service.processors.MenuCommandsProcessorImpl;
import ru.otus.education.service.processors.QuizProcessor;
import ru.otus.education.service.processors.ResultQuizProcessor;
import ru.otus.education.service.processors.StopApplicationSingleCommandProcessor;
import ru.otus.education.service.processors.StudentInfoProcessor;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {CsvConfig.class
        , MenuOptionsRegistryImpl.class
        , MenuCommandsProcessorImpl.class
        , ApplicationStopServiceImpl.class
        , MenuOptionCollectionConfig.class
        , AppConfig.class
        , QuizServiceImpl.class
        , StudentInfoProcessor.class
        , MenuCommandsProcessorImpl.class
        , ResultQuizProcessor.class
        , QuizProcessor.class
        , StopApplicationSingleCommandProcessor.class
        , CsvTransfer.class})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class QuizServiceImplTest {

    private final QuizService quizService;
    private final StudentInfoProcessor studentInfoProcessor;
    private final ApplicationStopService applicationStopService;
    private final QuizProcessor quizProcessor;
    private final StopApplicationSingleCommandProcessor stopApplicationSingleCommandProcessor;


    @DisplayName("должен корректно добавлять параметры пользователя")
    @Test
    void shouldCorrectAddUserInfoToExpectedServicesMethods() throws NoSuchFieldException, IllegalAccessException {
        var expectedSurname = "Goniak";
        var expectedName = "Dmitrii";
        String finalLine = expectedSurname + "\n" + expectedName;
        ByteArrayInputStream in = new ByteArrayInputStream(finalLine.getBytes());
        System.setIn(in);
        studentInfoProcessor.processCommand();
        Field f = quizService.getClass().getDeclaredField("resultQuiz"); //NoSuchFieldException
        f.setAccessible(true);
        ResultQuiz resultQuiz = (ResultQuiz) f.get(quizService);

        assertThat(resultQuiz.getUser().getSurname()).isEqualTo(expectedSurname);
        assertThat(resultQuiz.getUser().getName()).isEqualTo(expectedName);
    }

    @DisplayName("должен корректно проходить тестирование")
    @Test
    void shouldCorrectPassTheTest() throws NoSuchFieldException, IllegalAccessException {
        quizService.setUser(new User("test", "test"));
        var expectedFirstAnswer = "3\n";
        var expectedSecondAnswer = "3\n";
        var expectedThirdAnswer = "1\n";
        var expectedFourthAnswer = "2\n";
        var expectedFifthAnswer = "1\n";
        String finalString = expectedFirstAnswer +
                expectedSecondAnswer +
                expectedThirdAnswer +
                expectedFourthAnswer +
                expectedFifthAnswer;

        ByteArrayInputStream in = new ByteArrayInputStream(finalString.getBytes());
        System.setIn(in);
        quizProcessor.processCommand();
        Field resultQuizField = quizService.getClass().getDeclaredField("resultQuiz"); //NoSuchFieldException
        resultQuizField.setAccessible(true);
        ResultQuiz resultQuiz = (ResultQuiz) resultQuizField.get(quizService);
        assertThat(resultQuiz.isPassed()).isTrue();
        assertThat(resultQuiz.isDone()).isTrue();
        assertThat(resultQuiz.getRightAnswers()).isEqualTo(5);
    }

    @DisplayName("должен корректно завершать работу программы")
    @Test
    void shouldReturnResultOfTesting() throws NoSuchFieldException, IllegalAccessException {
        String finalString = "yes";
        ByteArrayInputStream in = new ByteArrayInputStream(finalString.getBytes());
        System.setIn(in);

        stopApplicationSingleCommandProcessor.processCommand();
        Field executionFlagField = applicationStopService.getClass().getDeclaredField("executionFlag");
        executionFlagField.setAccessible(true);
        AtomicBoolean executionFlag = (AtomicBoolean) executionFlagField.get(applicationStopService);
        assertThat(executionFlag).isFalse();
    }
}
