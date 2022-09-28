package com.appointemnt.perennial.globalexceptions;

import com.appointemnt.perennial.customexceptions.UserHandlingException;
import com.appointemnt.perennial.dto.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This class is for global exception handling in the application
 *
 * @author Mauli satav. <mauli.satav@perennialsys.com>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        ErrorResponse resp = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleEmptyDataException(EmptyResultDataAccessException e) {
        ErrorResponse resp = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

    @ExceptionHandler(UserHandlingException.class)
    public ResponseEntity<?> handleUserHandlingException(UserHandlingException e) {
        ErrorResponse resp = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

    @ExceptionHandler (value = {HttpClientErrorException.Unauthorized.class})
    public ResponseEntity<?> handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response,
                                            AccessDeniedException accessDeniedException) throws IOException {
        // 403
      ErrorResponse response1 = new ErrorResponse("No authorization in token",LocalDateTime.now());
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response1);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder sb = new StringBuilder("Validation Errors : ");
        ex.getBindingResult().getFieldErrors().forEach(e -> sb.append(e.getDefaultMessage() + " "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(sb.toString(), LocalDateTime.now()));
    }

}

