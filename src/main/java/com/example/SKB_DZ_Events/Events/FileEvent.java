package com.example.SKB_DZ_Events.Events;

import org.springframework.context.ApplicationEvent;

public class FileEvent extends ApplicationEvent {

    private final String filePath;

    public FileEvent(Object source, String filePath) {
        super(source);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
