package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 */
public class PlagiarismReportJSON {
    private String firstUser;
    private String secondUser;
    private Double similarityScore;

    public String getFirstUser() {
        return firstUser;
    }

    private void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    public PlagiarismReportJSON withFirstUser(String firstUser){
        this.setFirstUser(firstUser);
        return this;
    }

    public String getSecondUser() {
        return secondUser;
    }

    private void setSecondUser(String secondUser) {
        this.secondUser = secondUser;
    }

    public PlagiarismReportJSON withSecondUser(String secondUser){
        this.setSecondUser(secondUser);
        return this;
    }

    public Double getSimilarityScore() {
        return similarityScore;
    }

    private void setSimilarityScore(Double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public PlagiarismReportJSON withScore(Double score){
        this.setSimilarityScore(score);
        return this;
    }

    @Override
    public String toString(){
        return  "FirstUser :\t" + firstUser + "\n" +
                "secondUser :\t" + secondUser + "\n" +
                "score :\t" + similarityScore + "\n \n \n";
    }
}
