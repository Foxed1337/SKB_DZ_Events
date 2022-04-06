package com.example.SKB_DZ_Events;

import com.example.SKB_DZ_Events.Events.FileEvent;
import com.example.SKB_DZ_Events.Events.FormattingEvent;
import com.example.SKB_DZ_Events.Events.WritingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class EventsListener {

    private final Logger LOGGER = Logger.getLogger(EventsListener.class.getName());
    @Autowired
    FormattedTextWriter writer;


    @EventListener
    public void handleWritingEvent(WritingEvent event) {
        LOGGER.info("[event handling - WritingEvent]");
        String text = event.getTextToWrite();
        for (int i = 0; i < text.length(); i++) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                LOGGER.info(e.getMessage());
            }
            System.out.print(text.charAt(i));
        }
    }

    @EventListener
    @Async
    public void handleFormattingEvent(FormattingEvent event) {
        LOGGER.info("[event handling - FormattingEvent]");
        System.out.println("Formatted text - " + event.getFormattedText());
        writer.writeToFile(event.getFormattedText());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransactionalIsSuccessfully(FileEvent event) {
        LOGGER.info("[event handling - handleTransactionalIsSuccessfully]");
        LOGGER.info("File was created successfully and formatted text was written to the file");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleTransactionalIsNotSuccessfully(FileEvent event) {
        LOGGER.info("[event handling - handleTransactionalIsNotSuccessfully]");
        File file = new File(event.getFilePath());
        if (file.delete()) {
            LOGGER.info("File with path: " + event.getFilePath() + " has been deleted");
        } else LOGGER.info("File with path: " + event.getFilePath() + " hasn't been deleted");
    }
}
