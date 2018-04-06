package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.strategies.StrategyFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

/**
 * @author Praveen Singh
 */
@Component
public class FileMetaData implements SimilarityStrategy{
    private static final Logger LOGGER = LogManager.getLogger(StrategyFactory.class);

    @Override
    public double calculateSimilarity(String file1, String file2) {
        String owner1 = getFileAuthor(file1);
        String owner2 = getFileAuthor(file2);
        if(owner1.equals(owner2))
            return 100.0;
        return 0.0;
    }

    private String getFileAuthor(String filePath){
        Path path = Paths.get(filePath);
        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        UserPrincipal owner = null;
        String ownerName="";
        try {
            owner = ownerAttributeView.getOwner();
            ownerName = owner.getName();
        } catch (IOException e) {
            LOGGER.error("Error reading file {}", filePath);
        }
        return ownerName;
    }
}
