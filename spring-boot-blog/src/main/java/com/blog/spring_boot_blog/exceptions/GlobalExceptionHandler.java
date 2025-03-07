package com.blog.spring_boot_blog.exceptions;

import com.blog.spring_boot_blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        String message=resourceNotFoundException.getMessage();
        ApiResponse  apiResponse=new ApiResponse(message,false);
        return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }
}
