/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastApi.demo.controller;

import fastApi.demo.CustomErrors.CustomException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Ruisu's
 */
@ControllerAdvice
public class ErrorsController {
    
    @ExceptionHandler( MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleErrorArguments(MethodArgumentNotValidException ex){
        Map<String, String> message = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            message.put(fieldName, errorMessage);
        });
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    
    @ExceptionHandler( CustomException.class )
    public ResponseEntity<String> handleExceptionCustom(RuntimeException error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
    
    @ExceptionHandler( Exception.class )
    public ResponseEntity<String> handleExceptionCustom(Exception error){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
    
}
