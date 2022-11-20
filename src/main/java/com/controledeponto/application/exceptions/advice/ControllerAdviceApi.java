package com.controledeponto.application.exceptions.advice;

import com.controledeponto.application.exceptions.service.ServiceException;
import com.controledeponto.application.exceptions.service.SeviceErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerAdviceApi {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<SeviceErro> resourceNotFound(ServiceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        SeviceErro err = new SeviceErro(String.valueOf(status), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<SeviceErro> resourceNotFound(ConstraintViolationException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        SeviceErro err = new SeviceErro(String.valueOf(status), "Campos Obrigatórios em branco");
        return ResponseEntity.status(status).body(err);
    }
}
