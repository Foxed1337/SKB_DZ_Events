package com.example.SKB_DZ_Events;

import com.example.SKB_DZ_Events.Events.FileEvent;
import com.example.SKB_DZ_Events.Exeptions.WritingFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Component
public class FormattedTextWriter {

    private final Logger LOGGER = Logger.getLogger(FormattedTextWriter.class.getName());

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional(rollbackFor = WritingFileException.class, propagation = Propagation.REQUIRED)
    public void writeToFile(String formattedText) {
        Path path = Paths.get("result.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            LOGGER.info("Start writing to the file");
            doExpensiveWork();
            publisher.publishEvent(new FileEvent(this, path.toAbsolutePath().toString()));
            LOGGER.info("[event - FileEvent - published]");
            writer.write(formattedText);
        } catch (IOException | WritingFileException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private void doExpensiveWork() throws WritingFileException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.info(e.getMessage());
        }

        //Как-будто что-то пошло не так
        boolean isSuccessful = Math.random() > 0.3f;
        if (!isSuccessful) {
            throw new WritingFileException();
        }
    }
}
