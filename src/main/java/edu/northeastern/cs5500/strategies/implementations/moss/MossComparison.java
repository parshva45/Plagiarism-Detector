package edu.northeastern.cs5500.strategies.implementations.moss;

import edu.northeastern.cs5500.strategies.SimilarityStrategy;
import edu.northeastern.cs5500.utils.Constants;
import it.zielke.moji.MossException;
import it.zielke.moji.SocketClient;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Collection;

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
        try {
            socketClient.run();
        } catch (IOException e) {
            LOGGER.error("Error creating connection");
        }
        for (File f : files) {
            try {
                socketClient.uploadFile(f);
            } catch (IOException e) {
                LOGGER.error("Input output exception {}", e.getMessage());
            }
        }
        try {
            socketClient.sendQuery();
        } catch (IOException e) {
            LOGGER.error("Error sending query");
        }
        LOGGER.info("returning socket url {}",socketClient.getResultURL());
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
        return 0.0;
    }

    /**
     * Move the files file1 and file2 to a parent directory which is created in this method
     * @param file1 is the path of file1
     * @param file2 is the path of file2
     * @return the path of the parent directory
     */
    private String createParentDir(String file1, String file2) {
        String directory = env.getProperty("moss.temp.location");
        LOGGER.info("started copying files to  {}", directory);
        File file = new File(directory);
        try {
            if (new File(directory).exists()) {
                FileUtils.cleanDirectory(file);
                FileUtils.forceDelete(file); //delete directory
            }
            FileUtils.forceMkdir(file); //create directory
            LOGGER.info("copying {} to {}", new File(file1).toPath(),
                    (new File(String.format("%s/%s", directory, new File(file1).getName()))).toPath());

            Files.copy(new File(file1).toPath(),
                    (new File(String.format("%s/%s", directory, new File(file1).getName()))).toPath());

            LOGGER.info("copying {} to {}", file2, file.getPath());

            Files.copy(new File(file2).toPath(),
                    (new File(String.format("%s/%s", file, new File(file2).getName()))).toPath());

        } catch (IOException e) {
            LOGGER.error("Error in moving files to a tmp location error {}", e);
        }
        LOGGER.info("completed copying files to the location mentioned {}", file.getPath());
        return file.getPath();
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
            LOGGER.error("exception happened in getting moss url {}", e.getMessage());
        }
        return "";
    }

}