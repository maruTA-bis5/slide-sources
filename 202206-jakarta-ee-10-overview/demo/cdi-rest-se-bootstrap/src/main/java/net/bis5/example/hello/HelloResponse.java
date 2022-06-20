package net.bis5.example.hello;

import java.time.LocalDateTime;

public class HelloResponse {

    private final String message;
    private final LocalDateTime current;

    public HelloResponse(String message, LocalDateTime current) {
        this.message = message;
        this.current = current;
    }

    public String getMessage() { return message; }
    public LocalDateTime getCurrent() { return current; }

}
