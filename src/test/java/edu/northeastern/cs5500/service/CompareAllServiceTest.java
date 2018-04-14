package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.StudentHomeWorkRepository;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.PlagiarismReportJSON;
import edu.northeastern.cs5500.strategies.StrategyTypes;
import edu.northeastern.cs5500.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class CompareAllServiceTest {

    @Mock
    private StudentHomeWorkRepository studentHomeWorkRepository;

    @Mock
    private FileComparisonService fileComparisonService;

    @Mock
    private Environment env;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CompareAllService compareAllService;

    @Test
    public void getAllResultAsyncShouldWorkAsExpected(){
        int userId = 1;
        User professor = new User().withUserName("praveen").withEmail("prave@gmail.com");
        User user1 = new User().withUserName("prav");
        User user2 = new User().withUserName("sing");

        StudentHomeWork studentHomeWork1 = new StudentHomeWork().withPath("file1").withUserId(1);
        StudentHomeWork studentHomeWork2 = new StudentHomeWork().withPath("file1").withUserId(2);
        List<StudentHomeWork> studentHomeWorkList = new ArrayList<>();
        studentHomeWorkList.add(studentHomeWork1);
        studentHomeWorkList.add(studentHomeWork2);

        List<PlagiarismReportJSON> reportJSONS = new ArrayList<>();
        reportJSONS.add(new PlagiarismReportJSON()
                .withFirstUser(user1.getUserName()).withSecondUser(user2.getUserName())
                .withScore(40.0));
        reportJSONS.add(new PlagiarismReportJSON()
                .withFirstUser(user2.getUserName()).withSecondUser(user1.getUserName())
                .withScore(50.0));



        when(userRepository.findById(userId)).thenReturn(professor);
        when(env.getProperty(Constants.PLAGIARISM_THRESHHOLD)).thenReturn("40.0");
        when(studentHomeWorkRepository.findAllByCourseIdAndAndHomeWorkId(1, 1))
                .thenReturn(studentHomeWorkList);
        when(fileComparisonService.compareTwoFilesByGivenStrategy(StrategyTypes.LEVENSHTEIN_DISTANCE.toString(),
                studentHomeWork1.getFilePath(), studentHomeWork2.getFilePath())).thenReturn(50.0);
        when(fileComparisonService.compareTwoFilesByGivenStrategy(StrategyTypes.LEVENSHTEIN_DISTANCE.toString(),
                studentHomeWork2.getFilePath(), studentHomeWork1.getFilePath())).thenReturn(50.0);
        when(userRepository.findById(studentHomeWork1.getUserId())).thenReturn(user1);
        when(userRepository.findById(studentHomeWork2.getUserId())).thenReturn(user2);

        doNothing().when(emailService).sendEmail(professor.getUserName(), professor.getEmail(),
                reportJSONS);

        compareAllService.process(userId);
    }

}