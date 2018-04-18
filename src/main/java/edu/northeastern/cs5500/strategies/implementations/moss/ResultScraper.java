package edu.northeastern.cs5500.strategies.implementations.moss;

import edu.northeastern.cs5500.utils.Constants;
import it.zielke.moji.MossException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author namratabilurkar
 */

public class ResultScraper {

    /**
     * constant variables
     */
    private static final String USER_AGENT =
            Constants.SCRAPING_AGENT;
//    private static final int TIME_OUT_TIME = 30000;
    private static final int TIME_OUT_TIME = Constants.WAIT_TIME;
    private static final Logger LOGGER = Logger.getLogger(Scraper.class.getName());

    /**
     * declaration of variables
     */
//    private List<String> fileNames = new LinkedList<>();
//    private List<MatchStorerMap> psList = new LinkedList<>();
    private MatchStorerMap psList = new MatchStorerMap();
    private int rowCounter = 0;


    /**
     * This class takes in a url and generates a jsoup document which is passed to performCrawling method
     * @param link the link to crawl
     * @return the list of MatchStorerMap that includes data to generate a plagiarism report
     */

//    public List<MatchStorerMap> startScraping(String link){
    public MatchStorerMap startScraping(String link){

        if (link.startsWith("http://moss.stanford.edu/results/")) {
            //Establishes connection with the given URL
            Connection conn = Jsoup.connect(link).timeout(TIME_OUT_TIME).userAgent(USER_AGENT).followRedirects(false);

            Document document;
            try {
                //Fetches the HTML Document from the given URL
                document = conn.get();
            } catch (IOException e) {
                LOGGER.info("Unable to connect to the document");
                return psList;
            }
            if (conn.response().statusCode() >= 300 && conn.response().statusCode() < 400) {
                //Redirection code for HTTP lies between 300 and 400.

//                LOGGER.warning("PAGE IS REDIRECTING. CANCELING CRAWLING FOR THIS PAGE");
                LOGGER.info("Abort crawling, page is redirecting");
//                return Collections.emptyList();
            } else if (conn.response().statusCode() == 200) {
                //OK code for HTTP is 200
                LOGGER.info("Connection successful, page is being scraped");
            }

            return performCrawling(document);
        } else {
            LOGGER.info("Not a MOSS result page");
            return psList;
        }
    }

    private MatchStorerMap performCrawling(Document document){

        Element resultTable;
        Elements tableRows;

        resultTable = document.select("table").get(0);
        tableRows = resultTable.select("tr");
        Element row1col1 = tableRows.get(0);
//        System.out.println(row1col1.text());
        String[] urls = row1col1.text().split(" ");
        System.out.println(urls[0]);
        System.out.println(urls[2]);
//        MatchStorerMap matchStorerMap1 = new MatchStorerMap();
//        MatchStorerMap matchStorerMap2 = new MatchStorerMap();
//        matchStorerMap1.setFileName(urls[0]);
//        matchStorerMap2.setFileName(urls[2]);

        for (Element td : tableRows){

//            MatchStorerMap ps = new MatchStorerMap();
            try {

//                Element url = td.selectFirst("a");
                Element url = td.select("a").first();
                String finalUrl = url.absUrl("href");

                Elements firstRow = td.select("tr");
                Elements cols = td.select("td");
//                String row1col1 = td.html();
//                Element row1col0 = td.select("td").get(0);
                Element row1col0 = cols.get(0);
//                Element row1col2 = td.select("td").get(2);
                Element row1col2 = cols.get(2);
                Element row1col1Inside = firstRow.get(0);

                System.out.println("HERE:" + row1col0.text()); // 25-33
                System.out.println(row1col2.text()); // 156-162
//                System.out.println(row1col1Inside.text()); // 25-33 156-162
//                ps.setLink(finalUrl);

//                for (Element col : cols){
//                    processColumn(col, ps);
//                }
                System.out.println("Cols size: " + cols.size());
                for (int i=0; i<cols.size(); i+=2) {
                    System.out.println("In first for loop");
//                    processColumn(cols.get(i), matchStorerMap1);
                    if (i%2 == 0) {
                        processColumn(cols.get(i), psList);
                    } else {
                        processColumn(cols.get(i+2), psList);
                    }
                }

//                for (int i=1; i<cols.size(); i+=2) {
//                    System.out.println("In second for loop");
//                    processColumn(cols.get(i), psList);
////                    processColumn(cols.get(i), matchStorerMap2);
//                }

//                psList.add(ps);

            } catch (Exception e){
                if (rowCounter == 0){
                    Elements fileNm = td.select("th");
//                    for (Element fn : fileNm){
//                        fileNames.add(fn.text());
//                    }
                }
            }
            rowCounter++;
        }
//        if (!fileNames.isEmpty()) {
//            fileNames.remove(fileNames.size() - 1);
//        }

        return psList;
    }

    private void processColumn(Element column, MatchStorerMap msMap){
        System.out.println("In process column");
        try {
            String line = column.text();
            String[] lineNum = line.split("-");
            Integer startLineNum = Integer.parseInt(lineNum[0]);
            Integer endLineNum = Integer.parseInt(lineNum[1]);
            ArrayList<Integer> lineNums = new ArrayList<>();
            for (int i =startLineNum; i<=endLineNum; i++) {
                lineNums.add(i);
            }
//            msMap.setListOfMatchingLineNums(lineNums);
//            ArrayList<Integer> intList = new
//            LineMatchMap lineMatchMap = new LineMatchMap();
//            lineMatchMap.setStartLineNum(Integer.parseInt(startLineNum));
//            lineMatchMap.setEndLineNum(Integer.parseInt(endLineNum));
//            List<LineMatchMap> lineMatchMapList = new List<LineMatchMap>();
//            msMap.setListOfMatchingLineNums(lineMatchMapList.add(lineMatchMap));


            System.out.println("Line: " + line);
            System.out.println(msMap.toString());
            System.out.println(msMap.getMatchingLineNums().toString());
            System.out.println("After printing msMap");

            String filename = line.substring(0, line.length() - 6);
//            String pc = line.substring(line.length() - 4, line.length() - 1);
            String pc = line.substring(line.length() - 3, line.length() - 1);
            System.out.println(filename);
            System.out.println(line);
            System.out.println(pc);
        } catch (Exception e){
            int lineCopied = Integer.parseInt(column.text());
//            msMap.setLinesCopied(lineCopied);
        }
        return;
    }


    /**
     * Comment out this method, pass the URL obtained in MossComparison to a call for the ResultScraper
     * and append "match0-top.html" to the obtained URL and start the scraping method on it.
     */
    public static void main(String...args) throws MossException {
//        MossApi moss = new MossApi();
////        URL url  = moss.getMossURL("../userdemo/test");
//        String filePath = "/Users/namratabilurkar/Academics/MSD/Project/team-207/src/test/resources/moss/";
//        URL url  = moss.getMossURL(filePath);
        ResultScraper resultScraper = new ResultScraper();
        String url = "http://moss.stanford.edu/results/";
        resultScraper.startScraping(url + "36178691/" + "match0-top.html");
    }
}
