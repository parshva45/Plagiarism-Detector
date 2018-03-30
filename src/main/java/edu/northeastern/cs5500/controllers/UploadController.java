package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.UploadAssignmentService;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/homework")
public class UploadController {
    private static final Logger LOGGER = LogManager.getLogger(UploadController.class);

    private final UploadAssignmentService uploadAssignmentService;

    @Autowired
    public UploadController(UploadAssignmentService uploadAssignmentService) {
        this.uploadAssignmentService = uploadAssignmentService;
    }

    /**
     * Rest Endpoint Method to upload homework for a user.
     * @param response Response Entity
     * @param file Multipart file
     * @param userId Integer
     * @param hwId Integer
     * @param courseId Integer
     * @throws IOException Throws an exception
     */
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public void uploadFile(HttpServletResponse response,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("userId") int userId,
                           @RequestParam("hwId") int hwId,
                           @RequestParam("courseId") int courseId) throws IOException {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if ("zip".equals(extension) || "py".equals(extension)) {
            try {
                uploadAssignmentService.uploadAssignment(file, userId, hwId, courseId);
            } catch (IOException e) {
                LOGGER.error("failed to upload");
            }
            LOGGER.info("successFull uploaded assignment");
        }else {
            LOGGER.info("not a python or zip file");
        }
        response.sendRedirect("/#/profile/" + userId);

    }

}