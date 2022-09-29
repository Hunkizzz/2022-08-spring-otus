package ru.otus.education.processors;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.education.model.User;
import ru.otus.education.service.InternationalService;
import ru.otus.education.service.QuizService;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.processors.StudentInfoProcessor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("Процессор команды внесения данных пользователя")
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class AddUserCommandProcessorTest {

    @Mock
    private MenuOption processedCommandOption;

    @Mock
    private QuizService quizService;

    @Mock
    private InternationalService internationalService;

    @InjectMocks
    private StudentInfoProcessor processor;


    @DisplayName("должен корректно добавлять параметры пользователя")
    @Test
    void shouldCorrectAddUserInfoToExpectedServicesMethods() {
        var expectedSurname = "Goniak";
        var expectedName = "Dmitrii";
        String finalLine = expectedSurname + "\n" + expectedName;
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(finalLine.getBytes());
        System.setIn(in);
        processor.processCommand();

        var captor = ArgumentCaptor.forClass(User.class);
        verify(quizService).setUser(captor.capture());
        var actualUser = captor.getValue();
        assertThat(actualUser).extracting(User::getSurname).isEqualTo(expectedSurname);
        assertThat(actualUser).extracting(User::getName).isEqualTo(expectedName);
    }

    @DisplayName("должен возвращает ожидаемый тип обрабатываемой команды")
    @Test
    void shouldReturnExpectedProcessedCommandOption() {
        assertThat(processor.getProcessedCommandOption()).isEqualTo(processedCommandOption);
    }
}