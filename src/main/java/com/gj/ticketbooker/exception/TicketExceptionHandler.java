package com.gj.ticketbooker.exception;

import com.gj.ticketbooker.model.ErrorResponse;
import com.gj.ticketbooker.runner.ThreadAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/*
This class acts as the exception handler
 */

@ControllerAdvice
public class TicketExceptionHandler {

    Logger logger = LoggerFactory.getLogger(TicketExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        ThreadAdapter.keepThreadActive = false;
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .error("Bad Request")
                .path(request.getRequestURL().toString())
                .time(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        logger.error("error", ex.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
