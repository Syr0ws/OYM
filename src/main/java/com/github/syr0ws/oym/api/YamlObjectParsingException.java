package com.github.syr0ws.oym.api;

public class YamlObjectParsingException extends Exception {

    public YamlObjectParsingException(String message) {
        super(message);
    }

    public YamlObjectParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public YamlObjectParsingException(Throwable cause) {
        super(cause);
    }
}
