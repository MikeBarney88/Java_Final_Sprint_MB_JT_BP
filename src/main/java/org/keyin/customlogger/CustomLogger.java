package org.keyin.customlogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomLogger {
    public static final String LOG_FILE = "logs/applicationlog.txt";

    /**
     * Logs an error message to the application log file.
     * This method should write the provided error message to the log file,
     * prefixed with an appropriate error label (e.g., "ERROR:").
     * Ensure the message is appended to the file and handle any I/O exceptions.
     * @param message The error message to log.
     * @throws IOException If an I/O error occurs while writing to the log file.
     */
    public static void logError(String message) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bufferedWriter.write("ERROR: " + message);
            bufferedWriter.newLine();
        }
    }

    /**
     * Logs an informational message to the application log file.
     * This method should write the provided informational message to the log file,
     * prefixed with an appropriate info label (e.g., "INFO:").
     * Ensure the message is appended to the file and handle any I/O exceptions.
     * @param message The info message to log.
     * @throws IOException If an I/O error occurs while writing to the log file.
     */
    public static void logInfo(String message) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bufferedWriter.write("INFO: " + message);
            bufferedWriter.newLine();
        }
    }
}