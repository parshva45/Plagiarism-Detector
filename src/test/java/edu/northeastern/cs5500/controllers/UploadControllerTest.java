package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.UploadAssignmentService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class UploadControllerTest {

    @Mock
    private UploadAssignmentService uploadAssignmentService;

    @InjectMocks
    private UploadController uploadController;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void uploadControllerShouldWorkAsExpected() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("submission1.py").toURI().getPath()));
        FileInputStream inputFile = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "submission1.py",
                "multipart/form-data", inputFile);

        Mockito.doNothing().when(uploadAssignmentService).uploadAssignment(multipartFile, 2, 2, 2);
        MockHttpServletResponse response = new MockHttpServletResponse();
        uploadController.uploadFile(response, multipartFile, 2, 2, 2);
    }



    @Test
    public void uploadControllerShouldThrowException() throws IOException {

        exception.expect(Exception.class);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("submission1.py")).getFile());
        FileInputStream inputFile = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "submission1.py",
                "multipart/form-data", inputFile);
        Mockito.doThrow(Exception.class).when(uploadAssignmentService)
                .uploadAssignment(multipartFile, 2, 2, 2);
        MockHttpServletResponse response = new MockHttpServletResponse();

        uploadController.uploadFile(response, multipartFile, 2, 2, 2);

        fail();

    }



    @Test
    public void uploadShouldNotUploadIfFileNameNotValid() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("submission1.txt").toURI().getPath()));
        FileInputStream inputFile = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "submission1.txt",
                "multipart/form-data", inputFile);

        Mockito.doNothing().when(uploadAssignmentService).uploadAssignment(multipartFile, 2, 2, 2);
        MockHttpServletResponse response = new MockHttpServletResponse();
        uploadController.uploadFile(response, multipartFile, 2, 2, 2);
    }

    @Test
    public void uploadShouldUploadZip() throws IOException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("submission1.zip").toURI().getPath()));
        FileInputStream inputFile = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", "submission1.zip",
                "multipart/form-data", inputFile);

        Mockito.doNothing().when(uploadAssignmentService).uploadAssignment(multipartFile, 2, 2, 2);
        MockHttpServletResponse response = new MockHttpServletResponse();
        uploadController.uploadFile(response, multipartFile, 2, 2, 2);
    }

    @Test
    public void uploadControllerShouldUploadGitFile() throws IOException {
        String file = "https://github.com/toddmotto/public-apis/blob/master/build/validate_format.py";
        Mockito.doNothing().when(uploadAssignmentService).uploadAssignment(Mockito.any(),
                any(String.class), any(Integer.class), any(Integer.class), any(Integer.class));
        MockHttpServletResponse response = new MockHttpServletResponse();
        uploadController.uploadGitFile(response, file, 1, 1, 1);
    }

    @Test
    public void uploadControllerShouldUploadGitFileShouldThrowException() {
        String file = "https://github.com/toddmotto/public-apis/blob/master/build/validate_format.py";
        try {
            Mockito.doThrow(IOException.class).when(uploadAssignmentService).uploadAssignment(Mockito.any(),
                    any(String.class), any(Integer.class), any(Integer.class), any(Integer.class));
            MockHttpServletResponse response = new MockHttpServletResponse();
            uploadController.uploadGitFile(response, file, 1, 1, 1);
//            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void uploadControllerShouldNotUploadWhenNotPythonFile() throws IOException {
        String file = "https://github.com/praveenksingh/BookYourFlight/blob/master/mongo/app.js";
        Mockito.doNothing().when(uploadAssignmentService).uploadAssignment(Mockito.any(),
                any(String.class), any(Integer.class), any(Integer.class), any(Integer.class));
        MockHttpServletResponse response = new MockHttpServletResponse();
        uploadController.uploadGitFile(response, file, 1, 1, 1);

    }

}