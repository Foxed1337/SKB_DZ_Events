package com.example.SKB_DZ_Events.Events;

import org.springframework.context.ApplicationEvent;

public class WritingEvent extends ApplicationEvent {
    private final String textToWrite;
    public WritingEvent(Object source, String textToWrite) {
        super(source);
        this.textToWrite = textToWrite;
    }

    public String getTextToWrite() {
        return textToWrite;
    }
}
