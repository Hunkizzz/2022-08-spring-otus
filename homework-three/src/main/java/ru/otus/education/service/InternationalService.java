package ru.otus.education.service;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
@ConfigurationProperties(prefix = "application")
@Data
public class InternationalService {
    private final MessageSource messageSource;
    private final Locale locale;

    public InternationalService(@Value("#{ systemProperties['user.language'] + '_' + systemProperties['user.country'] }")
                                @NotNull Locale locale,
                                @NotNull MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    @NotNull
    public String getMessage(@NotNull String code, @Nullable String[] args) {
        var msg = messageSource.getMessage(code, args, locale);
        return Objects.requireNonNullElse(msg, "");
    }
}
