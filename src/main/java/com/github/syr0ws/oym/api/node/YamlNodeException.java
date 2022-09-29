package com.github.syr0ws.oym.api.node;

public class YamlNodeException extends RuntimeException {

    public YamlNodeException(String message) {
        super(message);
    }

    public YamlNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public YamlNodeException(Throwable cause) {
        super(cause);
    }
}
