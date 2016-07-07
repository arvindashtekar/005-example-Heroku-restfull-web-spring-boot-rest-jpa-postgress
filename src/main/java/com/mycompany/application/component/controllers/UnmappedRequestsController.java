package com.mycompany.application.component.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.application.component.exceptions.UnknownResourceException;

/**
 * Default controller that exists to return a proper REST response for unmapped requests.
 * This class is throwing exception. Actual exception is handled in GlobalExceptionHandlerControllerAdvice
 */
@Controller
public class UnmappedRequestsController {

    @RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        throw new UnknownResourceException("There is no resource for path " + uri);
    }
}