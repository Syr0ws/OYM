package com.github.syr0ws.oym.api;

public class NodeParsingException extends Exception {

    public NodeParsingException(String message) {
        super(message);
    }

    public NodeParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeParsingException(Throwable cause) {
        super(cause);
    }
}
