package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.UploadAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/homework")
public class UploadController {

    private final Environment env;

    private final UploadAssignmentService uploadAssignmentService;

    @Autowired
    public UploadController(Environment env, UploadAssignmentService uploadAssignmentService) {
        this.env = env;
        this.uploadAssignmentService = uploadAssignmentService;
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(HttpServletResponse response,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("userId") int userId,
                           @RequestParam("hwId") int hwId,
                           @RequestParam("courseId") int courseId) throws IOException {

        try{
            uploadAssignmentService.uploadAssignment(file, userId, hwId, courseId);
        }catch (IOException e){
            System.out.println("failed to upload");
        }
        response.sendRedirect("/#/profile/"+userId);
    }

}