package edu.northeastern.cs5500.strategies.implementations;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
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
    @Override
    public double calculateSimilarity(String file1, String file2) {
        getFileAuthor(file1);
        return 0;
    }

    private String getFileAuthor(String filePath){
        Path path = Paths.get(filePath);
        FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        UserPrincipal owner = null;
        try {
            owner = ownerAttributeView.getOwner();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("owner: " + owner.getName());
        return "hello";
    }
}
