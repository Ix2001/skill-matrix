package com.example.skillmatrix.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("jakarta.servlet.error.exception");

        String errorMessage = "An error occurred";
        String errorTitle = "Error";

        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    errorTitle = "Not Found";
                    errorMessage = "The requested resource was not found";
                    break;
                case 403:
                    errorTitle = "Forbidden";
                    errorMessage = "Access to this resource is forbidden";
                    break;
                case 401:
                    errorTitle = "Unauthorized";
                    errorMessage = "Authentication is required";
                    break;
                case 400:
                    errorTitle = "Bad Request";
                    errorMessage = "The request is invalid";
                    break;
                case 500:
                    errorTitle = "Internal Server Error";
                    errorMessage = "An internal server error occurred";
                    break;
                default:
                    errorTitle = "Error";
                    errorMessage = "An unexpected error occurred";
            }
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", statusCode != null ? statusCode : 500);
        errorResponse.put("error", errorTitle);
        errorResponse.put("message", errorMessage);
        errorResponse.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,
                statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
