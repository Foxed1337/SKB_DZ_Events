package com.example.SKB_DZ_Events.Exeptions;

public class WritingFileException extends Exception {
    @Override
    public String getMessage() {
        return "\n----------\n!!!!!!EROR!!!!!\n----------\n";
    }
}
