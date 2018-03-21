package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.response.UploadFileJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/homework")
public class UploadController {

    private final Environment env;

    @Autowired
    public UploadController(Environment env) {
        this.env = env;
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UploadFileJSON> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile,
                                                     @RequestParam("userId") int userId,
                                                     @RequestParam("hwId") int hwId,
                                                     @RequestParam("courseId") int courseId) {
        String filepath;

        try {
            // Get the filename and build the local file path
            String filename = uploadFile.getOriginalFilename();
            String directory = env.getProperty("fileupload.paths.uploadedFiles");
            String path = String.format("%s/%d/%d/%d", directory, userId, courseId, hwId);
            filepath = Paths.get(path, filename).toString();

            File file = new File(filepath);
            Files.deleteIfExists(file.toPath());
            file.getParentFile().mkdirs();

            // Save the file locally
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
                stream.write(uploadFile.getBytes());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
              new UploadFileJSON().withMessage("Failed")
            );
        }

        return ResponseEntity.ok().body(
                new UploadFileJSON()
                        .withPath(filepath).withMessage("uploaded"));
    }

}