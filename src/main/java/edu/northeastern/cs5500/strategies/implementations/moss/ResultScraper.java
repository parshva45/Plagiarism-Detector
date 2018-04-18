package edu.northeastern.cs5500.strategies.implementations.moss;

import edu.northeastern.cs5500.utils.Constants;
import edu.northeastern.cs5500.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author namratabilurkar
 */

@Component
public class ResultScraper {
    private static final Logger LOGGER = LogManager.getLogger(ResultScraper.class);
    private static final String USER_AGENT = Constants.SCRAPING_AGENT;
    private static final int TIME_OUT_TIME = Constants.WAIT_TIME;

    /**
     * This class takes in a url and generates a jsoup document which is passed to performCrawling method
     * @param link the link to crawl
     * @return the list of MatchStorerMap that includes data to generate a plagiarism report
     */
    public Integer[][] startScraping(String link){
        LOGGER.info("started scraping link {}", link);
        if (link.startsWith("http://moss.stanford.edu/results/")) {
            Connection conn = Jsoup.connect(link).timeout(TIME_OUT_TIME).userAgent(USER_AGENT).followRedirects(false);
            Document document;
            try {
                document = conn.get();
            } catch (IOException e) {
                LOGGER.error("Unable to connect to the document");
                return new Integer[][]{{-1},{-1}};
            }
            if (conn.response().statusCode() >= 300 && conn.response().statusCode() < 400) {
                LOGGER.error("Abort crawling, page is redirecting");
            } else if (conn.response().statusCode() == 200) {
                LOGGER.info("Connection successful, page is being scraped");
            }
            return performCrawling(document);
        } else {
            LOGGER.error("Not a MOSS result page");
        }
        return new Integer[][]{{-1},{-1}};
    }

    private Integer[][] performCrawling(Document document){
        LOGGER.info("inside performCrawling for document {}", document.title());
        Element resultTable;
        Elements tableRows;
        List<Integer> firstFile = new ArrayList<>();
        List<Integer> secondFile = new ArrayList<>();

        resultTable = document.select("table").get(0);
        tableRows = resultTable.select("tr");
        Element row1col1 = tableRows.get(0);
        String[] urls = row1col1.text().split(" ");
        LOGGER.info("First File Name {}", urls[0]);
        LOGGER.info("Second File Name {}", urls[2]);

        for (Element td : tableRows){
            try {
                Elements cols = td.select("td");
                Element row1col0 = cols.get(0);
                Element row1col2 = cols.get(2);
                LOGGER.info("Common Lines from File 1 {}", row1col0.text());
                firstFile.addAll(processColumn(cols.get(0)));
                LOGGER.info("Common Lines from File 2 {}", row1col2.text());
                secondFile.addAll(processColumn(cols.get(2)));
            } catch (Exception e){
                LOGGER.error("exception in performCrawling - {}", e.getMessage());
            }
        }
        return Utils.getArrayFromList(firstFile, secondFile);
    }

    private List<Integer> processColumn(Element column){
        LOGGER.info("In process column");
        List<Integer> lineNums = new ArrayList<>();
        try {
            String line = column.text();
            String[] lineNum = line.split("-");
            Integer startLineNum = Integer.parseInt(lineNum[0]);
            Integer endLineNum = Integer.parseInt(lineNum[1]);
            for (int i =startLineNum; i<=endLineNum; i++) {
                lineNums.add(i);
            }
//            String filename = line.substring(0, line.length() - 6);
//            String pc = line.substring(line.length() - 3, line.length() - 1);
        } catch (Exception e){
            LOGGER.error("error in processColumn error {}", e.getMessage());
        }
        return lineNums;
    }

}
