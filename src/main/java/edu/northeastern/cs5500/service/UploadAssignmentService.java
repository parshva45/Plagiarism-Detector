package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.StudentHomeWork;
import edu.northeastern.cs5500.repository.StudentHomeWorkRepository;
import edu.northeastern.cs5500.response.UploadFileJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Praveen Singh
 */
@Service
public class UploadAssignmentService {

    private final StudentHomeWorkRepository studentHomeWorkRepository;

    private final Environment env;

    @Autowired
    public UploadAssignmentService(StudentHomeWorkRepository studentHomeWorkRepository, Environment env) {
        this.studentHomeWorkRepository = studentHomeWorkRepository;
        this.env = env;
    }

    public void uploadAssignment(MultipartFile file, int userId, int courseId, int hwId) throws IOException {

        String filepath;

        // Get the filename and build the local file path
        String filename = file.getOriginalFilename();
        String directory = env.getProperty("fileupload.paths.uploadedFiles");
        String path = String.format("%s/%d/%d/%d", directory, userId, courseId, hwId);
        filepath = Paths.get(path, filename).toString();

        File fileUpload = new File(filepath);
        Files.deleteIfExists(fileUpload.toPath());
        fileUpload.getParentFile().mkdirs();

        // Save the file locally
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileUpload))) {
            stream.write(file.getBytes());
            studentHomeWorkRepository.save(createStudentHomeWorkEntity(userId,
                    courseId, hwId, filepath));
        }

    }

    private StudentHomeWork createStudentHomeWorkEntity(int userId, int courseId,
                                                        int hwId, String path){
        return new StudentHomeWork()
                .withUserId(userId)
                .withCourseId(courseId)
                .withHomeWorkId(hwId)
                .withPath(path);
    }

}
