package de.dhbw_mannheim.student.exceptions;

import java.io.IOException;

public class FileIsDirectoryException extends IOException {

    public FileIsDirectoryException(String message) {
        super(message);
    }
}
