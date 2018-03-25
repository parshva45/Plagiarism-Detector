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
import java.util.Objects;

import java.net.URISyntaxException;

import static org.junit.Assert.fail;

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

}