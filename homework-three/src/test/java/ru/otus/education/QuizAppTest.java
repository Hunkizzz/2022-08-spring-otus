package ru.otus.education;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.education.exceptions.MenuCommandProcessorNotFound;
import ru.otus.education.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.education.service.ApplicationServiceImpl;
import ru.otus.education.service.ApplicationStopService;
import ru.otus.education.service.InternationalService;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.menu.MenuOptionsRegistry;
import ru.otus.education.service.processors.MenuCommandsProcessor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuizAppTest {
    @Mock
    private MenuOptionsRegistry menuOptionsRegistry;
    @Mock
    private MenuCommandsProcessor menuCommandsProcessor;
    @Mock
    private ApplicationStopService applicationStopService;
    @Mock
    private InternationalService internationalService;
    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(applicationStopService.isApplicationRunning()).thenReturn(true);
        Mockito.when(menuOptionsRegistry.getAvailableMenuOptions()).thenReturn(List.of
                (new MenuOption(1, "124")
                        , new MenuOption(3, "124")));
    }


    @DisplayName("должен кидать ожидаемое исключение если число задано неверно")
    @Test
    void shouldThrowExpectedExceptio() {
        var wrongNumber = "a12";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(wrongNumber.getBytes());
        System.setIn(in);
        assertThatCode(() -> applicationService.run())
                .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("должен кидать ожидаемое исключение если процессор для заданной команды отсутствует")
    @Test
    void shouldThrowExpectedExceptionWhenValueIsNotInList() {
        var wrongNumber = "2";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(wrongNumber.getBytes());
        System.setIn(in);
        assertThatCode(() -> applicationService.run())
                .isInstanceOf(MenuCommandProcessorNotFound.class);
    }

    @DisplayName("должен кидать ожидаемое исключение если процессор для заданной команды отсутствует")
    @Test
    void shouldThrowExpectedExceptionWhenValueIsNotNumber() {
        var wrongNumber = "12";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(wrongNumber.getBytes());
        System.setIn(in);
        assertThatCode(() -> applicationService.run())
                .isInstanceOf(MenuItemIndexOutOfBoundsException.class);
    }
}
