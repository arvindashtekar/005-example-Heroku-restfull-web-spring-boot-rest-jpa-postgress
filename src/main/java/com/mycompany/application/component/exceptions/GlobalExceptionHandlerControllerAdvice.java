package com.mycompany.application.component.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;  
  
@ControllerAdvice  
@RestController  
public class GlobalExceptionHandlerControllerAdvice {  
  
//    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    @ExceptionHandler(value = Exception.class)  
    @ResponseBody 
    ErrorInfo handleException(HttpServletRequest req, Exception e){
    	return new ErrorInfo(req.getRequestURL().toString(), e);
    }  
  
  
}