package com.example.telegrambotcourse.config;

import com.example.telegrambotcourse.TelegramBotCourse;
import com.example.telegrambotcourse.facade.TelegramFacade;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "bot")
public class TelegramBotConfig {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    private int maxThreads;

    @Bean
    public TelegramBotCourse telegramBotCourse(TelegramFacade telegramFacade) {
        TelegramBotCourse telegramBotCourse = new TelegramBotCourse(defaultBotOptions(), telegramFacade);

        telegramBotCourse.setBotUserName(botUserName);
        telegramBotCourse.setBotToken(botToken);
        telegramBotCourse.setWebHookPath(webHookPath);

        return telegramBotCourse;
    }

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        DefaultBotOptions options = new DefaultBotOptions();

        options.setMaxThreads(maxThreads);
        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        return options;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}