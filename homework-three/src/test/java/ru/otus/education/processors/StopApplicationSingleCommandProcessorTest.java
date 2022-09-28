package ru.otus.education.processors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.education.service.ApplicationStopServiceImpl;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.processors.StopApplicationSingleCommandProcessor;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StopApplicationSingleCommandProcessorTest {
    @Mock
    MenuOption processedCommandOption;
    @InjectMocks
    StopApplicationSingleCommandProcessor stopApplicationSingleCommandProcessor;
    @Mock
    private ApplicationStopServiceImpl applicationStopService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("должен корректно завершать работу программы")
    @Test
    void shouldReturnResultOfTesting() throws NoSuchFieldException, IllegalAccessException {
        String finalString = "yes";
        ByteArrayInputStream in = new ByteArrayInputStream(finalString.getBytes());
        System.setIn(in);
        stopApplicationSingleCommandProcessor.processCommand();
        boolean executionFlag = applicationStopService.isApplicationRunning();
        assertThat(executionFlag).isFalse();
    }
}
