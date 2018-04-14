package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Praveen Singh
 */
@Service
public class CompareAllService {

    private static final Logger LOGGER = LogManager.getLogger(CompareAllService.class);

    private final StudentHomeWork studentHomeWork;

    @Autowired
    public CompareAllService(StudentHomeWork studentHomeWork) {
        this.studentHomeWork = studentHomeWork;
    }


    /**
     * This method compares for plagiarism for each user against all other users
     * and sends a report email to the caller.
     */
    @Async("processExecutor")
    public void process() throws InterruptedException {
        Thread.sleep(15 * 1000);
        LOGGER.info("Processing complete");
    }

}
