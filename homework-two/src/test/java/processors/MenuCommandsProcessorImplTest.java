package processors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.education.exceptions.MenuCommandProcessorNotFound;
import ru.otus.education.service.menu.MenuOption;
import ru.otus.education.service.processors.MenuCommandsProcessorImpl;
import ru.otus.education.service.processors.MenuSingleCommandProcessor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Процессор команд меню ")
class MenuCommandsProcessorImplTest {

    private List<MenuSingleCommandProcessor> singleCommandProcessors;
    private MenuCommandsProcessorImpl processor;

    @BeforeEach
    void setUp() {
        singleCommandProcessors = new ArrayList<>(3);
        for (int i = 1; i < 4; i++) {
            var opt = new MenuOption(i, "opt" + i);
            var singleCommandProcessor = mock(MenuSingleCommandProcessor.class);
            given(singleCommandProcessor.getProcessedCommandOption()).willReturn(opt);
            singleCommandProcessors.add(singleCommandProcessor);
        }
        processor = new MenuCommandsProcessorImpl(singleCommandProcessors);
    }

    @DisplayName("должен корректно обрабатывать команду если для нее есть процессор")
    @Test
    void shouldCorrectProcessMenuCommandWhenProcessorForGivenCommandExists() {
        for (var singleCommandProcessor : singleCommandProcessors) {
            processor.processMenuCommand(singleCommandProcessor.getProcessedCommandOption());
            verify(singleCommandProcessor, times(1)).processCommand();
        }
    }

    @DisplayName("должен кидать ожидаемое исключение если процессор для заданной команды отсутствует")
    @Test
    void shouldThrowExpectedExceptionWhenProcessorForGivenCommandDoesNotExists() {
        var commandOptionWithNotExistingProcessor = new MenuOption(4, "opt4");
        assertThatCode(() -> processor.processMenuCommand(commandOptionWithNotExistingProcessor))
                .isInstanceOf(MenuCommandProcessorNotFound.class);
    }
}