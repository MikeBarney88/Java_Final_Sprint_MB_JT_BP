package org.keyin.customlogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomLogger {
    private static BufferedWriter bufferedWriter;

    /**
     * Logs an error message to the application log file.
     * This method should write the provided error message to the log file,
     * prefixed with an appropriate error label (e.g., "ERROR:").
     * Ensure the message is appended to the file and handle any I/O exceptions.
     * @param message The error message to log.
     * @throws IOException If an I/O error occurs while writing to the log file.
     */
    public static void logError(String message) throws IOException {
        // TODO: Write the error message to the log file, prefixed with "ERROR:"
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("org/keyin/customlogger/applicationlog.txt", true));
            writer.write("ERROR: " + message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new IOException(e);
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
        // TODO: Write the info message to the log file, prefixed with "INFO:"
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("org/keyin/customlogger/applicationlog.txt", true));
            writer.write("INFO: " + message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }


}
