package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.StudentHomeWorkRepository;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.PlagiarismReportJSON;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import edu.northeastern.cs5500.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praveen Singh
 */
@Service
public class CompareAllService {

    private static final Logger LOGGER = LogManager.getLogger(CompareAllService.class);

    private final StudentHomeWorkRepository studentHomeWorkRepository;
    private final FileComparisonService fileComparisonService;
    private final Environment env;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public CompareAllService(StudentHomeWorkRepository studentHomeWorkRepository,
                             FileComparisonService fileComparisonService,
                             Environment env, UserRepository userRepository,
                             EmailService emailService) {
        this.studentHomeWorkRepository = studentHomeWorkRepository;
        this.fileComparisonService = fileComparisonService;
        this.env = env;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    /**
     * This method compares for plagiarism for each user against all other users
     * and sends a report email to the caller.
     */
    @Async("processExecutor")
    public void process(Integer userId) {
        User professor = userRepository.findById(userId);
        LOGGER.info("process started by user {}, email will be sent to user", professor.getId());
        List<StudentHomeWork> studentHomeWorkList = studentHomeWorkRepository.findAllByCourseIdAndAndHomeWorkId(1, 1);
        List<PlagiarismReportJSON> plagiarismReportJSONList = new ArrayList<>();
        Double threshHold = Double.parseDouble(env.getProperty(Constants.PLAGIARISM_THRESHHOLD));
        for (StudentHomeWork studentHomeWork: studentHomeWorkList) {
            for (StudentHomeWork studentHomeWorkCompare: studentHomeWorkList) {

                if(studentHomeWork.getId() != studentHomeWorkCompare.getId()){
                    Double score = fileComparisonService.compareTwoFilesByGivenStrategy(
                            StrategyTypes.LEVENSHTEIN_DISTANCE.toString(),
                            studentHomeWork.getFilePath(),
                            studentHomeWorkCompare.getFilePath());
                    if(score >= threshHold){
                        LOGGER.info("plagiarism found between {} and {}", studentHomeWork.getUserId(), studentHomeWorkCompare.getUserId());
                        plagiarismReportJSONList.add(new PlagiarismReportJSON()
                                .withFirstUser(userRepository.findById(studentHomeWork.getUserId()).getUserName())
                                .withSecondUser(userRepository.findById(studentHomeWorkCompare.getUserId()).getUserName())
                                .withScore(score));
                    }
                }

            }
        }
        LOGGER.info("Processing complete");

        LOGGER.info("sending result email");
        emailService.sendEmail(professor.getUserName(), professor.getEmail(), plagiarismReportJSONList);
    }

}
