package edu.northeastern.cs5500.service;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author Praveen Singh
 */
@Component
public class CreateHtmlFromFileService {
    private static final Logger LOGGER = LogManager.getLogger(CreateHtmlFromFileService.class);

    public String createFileHtmString(String filePath, Integer[] lines){
        List linesList = Arrays.asList(lines);
        LOGGER.info("Reading file {}", filePath);
        StringBuilder contentBuilder = new StringBuilder();
        int count = 1;
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(linesList.contains(count))
                    contentBuilder.append("<div style=\"background-color:yellow\">").append(line);
                else
                    contentBuilder.append("<div>").append(line);
                contentBuilder.append("</div>").append("\n");
            }
        } catch (IOException ex) {
            LOGGER.error("Exception in creating HTML from file : {} error : {}", filePath, ex.getMessage());
        }
        return contentBuilder.toString();
    }


}
