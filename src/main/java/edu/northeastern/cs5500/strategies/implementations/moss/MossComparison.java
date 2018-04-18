package edu.northeastern.cs5500.strategies.implementations.moss;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.net.URL;
import java.nio.file.*;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.utils.Constants;
import it.zielke.moji.MossException;
import org.apache.commons.io.FileUtils;
import it.zielke.moji.SocketClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author namratabilurkar
 */
@Component
public class MossComparison implements SimilarityStrategy {
    private static final Logger LOGGER = LogManager.getLogger(MossComparison.class);

    private final Environment env;

    @Autowired
    public MossComparison(Environment env) {
        this.env = env;
    }

    /**
     *
     * @param filePath is the path of the directory containing the second submission's files
     * @return URL containing the MOSS result
     * @throws Exception Handles IO and MossException
     */
    private URL getMossURL(String filePath) throws MossException {
        LOGGER.info("started comparison for file  {}",filePath);
        SocketClient socketClient = new SocketClient();
        socketClient.setUserID(Integer.toString(Constants.MOSS_ID));
        socketClient.setLanguage(Constants.LANGUAGE);
        Collection<File> files = FileUtils.listFiles(new File(filePath), new String[]
                { Constants.LANGUAGE_CODE }, true);
        for (File f : files) {
            try {
                socketClient.run();
                socketClient.uploadFile(f);
                socketClient.sendQuery();
            } catch (IOException e) {
                LOGGER.error("Input output exception {}", e.getMessage());
            }
        }
        return socketClient.getResultURL();
    }


    /**
     *
     * @param file1 String containing the filepath of first submission
     * @param file2 String containing the filepath of second submission
     * @return the similarity score
     */
    @Override
    public double calculateSimilarity(String file1, String file2) {
        String path =  createParentDir(file1, file2);
        try {
            URL url = getMossURL(path);
        } catch (MossException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Move the files file1 and file2 to a parent directory which is created in this method
     * @param file1 is the path of file1
     * @param file2 is the path of file2
     * @return the path of the parent directory
     */
    private String createParentDir(String file1, String file2) {
        String directory = env.getProperty("fileupload.paths.uploadedFiles");
        File f = new File(directory);
        try {
            FileUtils.cleanDirectory(f);
            FileUtils.forceDelete(f); //delete directory
            FileUtils.forceMkdir(f); //create directory
            Files.move(Paths.get(file1), Paths.get(f.getPath()),
                    StandardCopyOption.REPLACE_EXISTING);
            Files.move(Paths.get(file2), Paths.get(f.getPath()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error("Error in moving files to a tmp location error {}", e.getMessage());
        }
        return f.getPath();
    }


    @Override
    public Integer[][] getSimilarLineNos(String file1, String file2) {
        return new Integer[][]{{-1},{-1}};
    }

    public String mossPlagiarismUrlForFiles(String file1, String file2) {
        String path =  createParentDir(file1, file2);
        try {
            URL url = getMossURL(path);
            return url.toString();
        } catch (MossException e) {
            LOGGER.error("exception occured in getting moss url {}", e.getMessage());
        }
        return "";
    }

}