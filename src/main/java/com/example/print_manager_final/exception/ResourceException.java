package com.example.print_manager_final.exception;

import com.example.print_manager_final.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceException {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> runtimeExceptionHandler(AppException e) {
        ErrorException errorException = e.getErrorException();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorException.getCode());
        apiResponse.setMessage(errorException.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    // Check exception cho validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> checkValidation(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorException errorException = ErrorException.INVALID_KEY;
        try {
            errorException = ErrorException.valueOf(enumKey);
        } catch (IllegalArgumentException exception) {

        }
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorException.getCode());
        apiResponse.setMessage(errorException.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
