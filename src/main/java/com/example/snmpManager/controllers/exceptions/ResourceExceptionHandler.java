package com.example.snmpManager.controllers.exceptions;

import com.example.snmpManager.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    //oid sem resposta
    @ExceptionHandler(UnableToGetDeviceDataException.class)
    public ResponseEntity<StandardError> failedToGetData(UnableToGetDeviceDataException e, HttpServletRequest request) {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Response OID not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    //falha iniciar mapeamento de transporte udp
    @ExceptionHandler(FailureInitializeUdpTransport.class)
    public ResponseEntity<StandardError> failedUdpTransport(FailureInitializeUdpTransport e, HttpServletRequest request) {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Failure initialize udp transport");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(InvalidAddressExecption.class)
    public ResponseEntity<StandardError> adrressException(InvalidAddressExecption e, HttpServletRequest request) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Invalid Adrress");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(UnprocesableEntityExecption.class)
    public ResponseEntity<StandardError> failedToProcessEntity(UnprocesableEntityExecption e, HttpServletRequest request) {
        int httpStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("UnprocessableEntity");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> failedToProcessEntity(DataBaseException e, HttpServletRequest request) {
        int httpStatus = HttpStatus.CONFLICT.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Database Exception");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        int httpStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();
        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Validation Exception");
        error.setMessage("Failed to validate arguments.");
        error.setPath(request.getRequestURI());

        //pega campo e mensagem de erro do obj interno da exception, e add no no obj FildMessage
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(httpStatus).body(error);
    }

}
