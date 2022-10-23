package com.github.syr0ws.oym.api.section;

public class ConfigurationSectionException extends Exception {

    public ConfigurationSectionException(String message) {
        super(message);
    }

    public ConfigurationSectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigurationSectionException(Throwable cause) {
        super(cause);
    }
}
