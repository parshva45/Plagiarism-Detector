package edu.northeastern.cs5500.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Praveen Singh
 */
@Service
public class StudentHomeWorkService {





    public static class HttpStatusException extends RuntimeException {
        private final HttpStatus status;

        public HttpStatusException(final HttpStatus status, final String message) {
            super(message);
            this.status = status;
        }

        public HttpStatus getStatus() {
            return status;
        }
    }
}
