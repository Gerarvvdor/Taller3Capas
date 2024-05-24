package com.nalas.pnccontrollers.handlers;

import com.nalas.pnccontrollers.domain.dtos.GeneralResponse;
import com.nalas.pnccontrollers.utils.ErrorsTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {
    private final ErrorsTool errorsTool;
    // Constructor dependency injection
    public GlobalErrorHandler(ErrorsTool errorsTool) {
        this.errorsTool = errorsTool;
    }

    @ExceptionHandler(Exception.class)  // It is used to define which exception class will be handled
    public ResponseEntity<GeneralResponse> GeneralHandler(Exception ex) {
        log.error(ex.getMessage());
        log.error(ex.getClass().getCanonicalName());

        return GeneralResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @ExceptionHandler(NoResourceFoundException.class) // It is used to define which exception class will be handled
    public ResponseEntity<GeneralResponse> ResourceNotFound(NoResourceFoundException ex) {
        return GeneralResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // It is used to define which exception class will be handled
    public ResponseEntity<GeneralResponse> BadRequestHandler(MethodArgumentNotValidException ex) {
        return GeneralResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .data(errorsTool.mapErrors(ex.getBindingResult().getFieldErrors()))
                .build();
    }
}
