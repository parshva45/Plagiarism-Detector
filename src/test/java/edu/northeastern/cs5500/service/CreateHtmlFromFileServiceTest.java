package edu.northeastern.cs5500.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Objects;

/**
 * @author Praveen Singh
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateHtmlFromFileServiceTest {

    @Autowired
    CreateHtmlFromFileService createHtmlFromFileService;

    /**
     * @param fileName of file to be get
     * @return absolute path of file
     */
    public String getFilePath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName))
                .getFile());
        return file.getAbsolutePath();
    }

    @Test
    public void createHtmlFromFileShouldReturnProper(){
        createHtmlFromFileService.createFileHtmString("test1.py", new Integer[]{1});
    }

}