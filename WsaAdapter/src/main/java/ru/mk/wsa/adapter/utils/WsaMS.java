package ru.mk.wsa.adapter.utils;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class WsaMS {

    private final ReloadableResourceBundleMessageSource bundleMessageSource;

    {
        bundleMessageSource = new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setBasename("/i18n/messages");
        locale= Locale.forLanguageTag("en");
    }

    private final static WsaMS WsaMessageService;

    static {
        WsaMessageService = new WsaMS();
    }

    private Locale locale;

    public String getMessage(String resourceKey) {
        return bundleMessageSource.getMessage(resourceKey,null, locale);
    }

    public String getMessage(String resourceKey, Object[] args) {
        return bundleMessageSource.getMessage(resourceKey, args, locale);
    }

    public static String getString(String resourceKey) {
        return WsaMessageService.getMessage(resourceKey);
    }

    public static String getString(String resourceKey, Object[] args) {
        return WsaMessageService.getMessage(resourceKey, args);
    }

    public static String getString(String resourceKey, Object arg) {
        return WsaMessageService.getMessage(resourceKey, new Object[] {arg});
    }

}
