package com.github.syr0ws.oym.api.node.parser;

/**
 * Exception raised when an error occurs while parsing objects into nodes.
 */
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
