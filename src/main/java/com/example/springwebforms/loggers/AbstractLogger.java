package com.example.springwebforms.loggers;

public abstract class AbstractLogger implements EventLogger {
    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
