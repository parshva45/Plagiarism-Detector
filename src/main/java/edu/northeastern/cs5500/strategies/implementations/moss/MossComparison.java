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

/**
 * @author namratabilurkar
 */

public class MossComparison implements SimilarityStrategy {

    private static final Logger LOGGER = LogManager.getLogger(MossComparison.class);

    @Autowired
    private MossComparison mossComparison;

    /**
     *
     * @param filepath1 is the path of the directory containing the first submission's files
     * @param filepath2 is the path of the directory containing the second submission's files
     * @return URL containing the MOSS result
     * @throws Exception Handles IO and MossException
     */
    public URL getMossURL(String filePath) throws MossException {

        SocketClient socketClient = new SocketClient();

        //set your Moss user ID
        socketClient.setUserID(Integer.toString(Constants.MOSS_ID));

        //set the programming language of all student source codes
        socketClient.setLanguage(Constants.LANGUAGE);

        Collection<File> files = FileUtils.listFiles(new File(filePath), new String[] { Constants.LANGUAGE_CODE }, true);

        //initialize connection and send parameters.
        try {
            socketClient.run();
        } catch (UnknownHostException e) {
            LOGGER.info("UnknowsHostException.");
        } catch (IOException e) {
            LOGGER.info("IOException");
        }

        //upload all source files of students
        for (File f : files) {
            try {
                socketClient.uploadFile(f);
            } catch (IOException e) {
                LOGGER.info("Input output exception.");
            }
        }

        //finished uploading, tell server to check files
        try {
            socketClient.sendQuery();
        } catch (IOException e) {
            LOGGER.info("IOException");
        }

        //get URL with Moss results and do something with it
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
                /*
                - Call the scraper method here using the two file paths that are given
                - Store the similarity percentage returned by that method here.
                - Scraper method will also extract the lists/arrays of similarity line numbers
                which gets stored as the second entry in the ArrayList.
                 */
//                mossComparison

                /*
                Add the two files into a new directory
                 */
        mossComparison.createParentDir(file1, file2);

        ResultScraper resultScraper = new ResultScraper();
        return 0.0;
    }

    /**
     * Move the files file1 and file2 to a parent directory which is created in this method
     * @param file1 is the path of file1
     * @param file2 is the path of file2
     * @return the path of the parent directory
     */
    private String createParentDir(String file1, String file2) {
//                new File("/Users/namratabilurkar/Academics/MSD/Project/team-207/src/test/resources/mossDir").mkdirs();
        new File("/Users/namratabilurkar/Academics/MSD/Project/"+
                "team-207/src/test/resources/mossDir").mkdir();
//                Files.move(Paths.get(file1), Paths.get(file2), StandardCopyOption.REPLACE_EXISTING);
        try {
            Files.move(Paths.get(file1),
                    Paths.get("/Users/namratabilurkar/Academics/MSD/Project/\"+\n" +
                            "                        \"team-207/src/test/resources/mossDir/"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.move(Paths.get(file2),
                    Paths.get("/Users/namratabilurkar/Academics/MSD/Project/\"+\n" +
                            "                        \"team-207/src/test/resources/mossDir/"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String parentDir = "/Users/namratabilurkar/Academics/MSD/Project/\"+\n" +
                "                        \"team-207/src/test/resources/mossDir/";
        String f1 = Paths.get(file1).getParent().toString();
        return parentDir;
    }


    @Override
    public Integer[][] getsimilarLineNos(String file1, String file2) {
        return new Integer[0][0];
    }

}