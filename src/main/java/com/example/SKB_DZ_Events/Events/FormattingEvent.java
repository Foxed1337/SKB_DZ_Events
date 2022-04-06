package com.example.SKB_DZ_Events.Events;

import org.springframework.context.ApplicationEvent;

public class FormattingEvent extends ApplicationEvent {

    private final String formattedText;

    public FormattingEvent(Object source, String formattedText) {
        super(source);
        this.formattedText = formattedText;
    }

    public String getFormattedText() {
        return formattedText;
    }

}
