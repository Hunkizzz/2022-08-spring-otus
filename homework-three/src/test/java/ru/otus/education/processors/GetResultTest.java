package ru.otus.education.processors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.education.config.MessageSourceConfig;
import ru.otus.education.model.ResultQuiz;
import ru.otus.education.model.User;
import ru.otus.education.service.QuizServiceImpl;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.processors.ResultQuizProcessor;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Процессор команды внесения данных пользователя")
@SpringBootTest(classes = {MessageSourceConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetResultTest {
    @Mock
    MenuOption processedCommandOption;
    @Mock
    QuizServiceImpl quizService;

    @InjectMocks
    ResultQuizProcessor resultQuizProcessor;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(quizService.getResult()).thenReturn(new ResultQuiz(new User("1", "1"), true, 5, true, true));
    }

    @DisplayName("должен корректно завершать работу программы")
    @Test
    void shouldReturnResultOfTesting() throws NoSuchFieldException, IllegalAccessException {
        String finalString = "yes";
        ByteArrayInputStream in = new ByteArrayInputStream(finalString.getBytes());
        System.setIn(in);
        resultQuizProcessor.processCommand();
        assertThat(quizService.getResult().getUser().getName()).isEqualTo("1");
        assertThat(quizService.getResult().getUser().getSurname()).isEqualTo("1");
        assertThat(quizService.getResult().isPassed()).isTrue();
        assertThat(quizService.getResult().isDone()).isTrue();
        assertThat(quizService.getResult().isUserDataSet()).isTrue();
        assertThat(quizService.getResult().getRightAnswers()).isEqualTo(5);
    }

}
