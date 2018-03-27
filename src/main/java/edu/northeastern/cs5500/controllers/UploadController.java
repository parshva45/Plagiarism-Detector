package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.UploadAssignmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/homework")
@Api(value="Upload Controller", description="Operations related to homework upload")
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
    @ApiOperation(value = "Upload homework with file.")
    public void uploadFile(HttpServletResponse response,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("userId") int userId,
                           @RequestParam("hwId") int hwId,
                           @RequestParam("courseId") int courseId) throws IOException {

        try{
            uploadAssignmentService.uploadAssignment(file, userId, hwId, courseId);
        }catch (IOException e){
            LOGGER.error("failed to upload");
        }
        LOGGER.info("successFull uploaded assignment");
        response.sendRedirect("/#/profile/"+userId);
    }

}