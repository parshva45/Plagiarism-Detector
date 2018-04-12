package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.service.UploadAssignmentService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

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
     * Rest Endpoint Method to upload homework for a user by choosing file.
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
    
    /**
     * Method to check if header points to redirection.
     * @param header Map<String, List<String>>
     * @return whether header is redirected or not boolean
     */
    private static boolean isRedirected( Map<String, List<String>> header ) {
        for( String hv : header.get( null )) {
           if(   hv.contains( " 301 " )
              || hv.contains( " 302 " )) return true;
        }
        return false;
     }
    
    /**
     * Rest Endpoint Method to upload homework for a user by providing Github URL of the submission file.
     * @param response Response Entity
     * @param gitLink String
     * @param userId Integer
     * @param hwId Integer
     * @param courseId Integer
     * @throws IOException Throws an exception
     */
    @RequestMapping(value = "/uploadgitfile", method = RequestMethod.POST)
    @ResponseBody
    public void uploadGitFile(HttpServletResponse response,
                           @RequestParam("link") String gitLink,
                           @RequestParam("userId") int userId,
                           @RequestParam("hwId") int hwId,
                           @RequestParam("courseId") int courseId) throws IOException {
    	
    	String link = gitLink.replaceFirst("blob", "raw");
        URL url = new URL(link);
        String fileName = FilenameUtils.getName(url.getPath());
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        Map<String, List<String>> header = http.getHeaderFields();
        while(isRedirected(header)) {
           link = header.get("Location").get(0);
           url = new URL(link);
           http = (HttpURLConnection)url.openConnection();
           header = http.getHeaderFields();
        }
        InputStream input = http.getInputStream();
        byte[] fileBytes = IOUtils.toByteArray(input);

        String extension = FilenameUtils.getExtension(url.getPath());
        if ("zip".equals(extension) || "py".equals(extension)) {
            try {
                uploadAssignmentService.uploadAssignment(fileBytes, fileName, userId, hwId, courseId);
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