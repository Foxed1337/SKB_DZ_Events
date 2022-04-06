package com.example.SKB_DZ_Events;

import com.example.SKB_DZ_Events.Events.FormattingEvent;
import com.example.SKB_DZ_Events.Events.WritingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.logging.Logger;

@Component
public class TextFormatter {

    private final Logger LOGGER = Logger.getLogger(TextFormatter.class.getName());

    @Autowired
    private ApplicationEventPublisher publisher;

    public String formatText(String text) {
        String formattedText = text.toUpperCase(Locale.ROOT).replace(' ', '_');
        publisher.publishEvent(new FormattingEvent(this, formattedText));
        publisher.publishEvent(new WritingEvent(this, formattedText));
        return formattedText;
    }
}
