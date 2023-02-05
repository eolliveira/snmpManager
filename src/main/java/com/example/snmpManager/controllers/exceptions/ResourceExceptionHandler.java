package com.example.snmpManager.controllers.exceptions;

import com.example.snmpManager.exceptions.FailureInitializeUdpTransport;
import com.example.snmpManager.exceptions.ResourceNotFoundException;
import com.example.snmpManager.exceptions.UnableToGetDeviceDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StandardError> entityNotFound(UnableToGetDeviceDataException e, HttpServletRequest request) {
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
    public ResponseEntity<StandardError> entityNotFound(FailureInitializeUdpTransport e, HttpServletRequest request) {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus);
        error.setError("Failure initialize udp transport");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }




//    @ExceptionHandler(DataBaseException.class)
//    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request) {
//        int httpStatus = HttpStatus.BAD_REQUEST.value();
//        StandardError error = new StandardError();
//        error.setTimestamp(Instant.now());
//        error.setStatus(httpStatus);
//        error.setError("Database exception");
//        error.setMessage(e.getMessage());
//        error.setPath(request.getRequestURI());
//        return ResponseEntity.status(httpStatus).body(error);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationError> arguments(MethodArgumentNotValidException e, HttpServletRequest request) {
//        int httpStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();
//        ValidationError error = new ValidationError();
//        error.setTimestamp(Instant.now());
//        error.setStatus(httpStatus);
//        error.setError("Arguments exception");
//        error.setMessage(e.getMessage());
//        error.setPath(request.getRequestURI());
//
//        //pega campo e mensagem de erro do obj interno da exception, e add no no obj FildMessage
//        for (FieldError f : e.getBindingResult().getFieldErrors()) {
//            error.addError(f.getField(), f.getDefaultMessage());
//        }
//
//        return ResponseEntity.status(httpStatus).body(error);
//    }
}
