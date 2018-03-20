package edu.northeastern.cs5500.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Praveen Singh
 */
@Component
public class CompareFilesLevenshtein {
    private static final Logger LOGGER = Logger.getLogger(CompareFilesLevenshtein.class.getName());

    /**
     * Method to read file and return content as a string for comparison.
     * @param filePath Path of File in string.
     * @return Returns the file content as a String.
     */
    public String readFile(String filePath){
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0)
                    contentBuilder.append(line).append("\n");
            }
        } catch (IOException ex) {
            LOGGER.log(Level.ALL, "Exception while reading file {0}", ex.getMessage());
        }
        return contentBuilder.toString();
    }

}
