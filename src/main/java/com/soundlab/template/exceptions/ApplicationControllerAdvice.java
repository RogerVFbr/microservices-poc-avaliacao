package com.soundlab.template.exceptions;

import org.jboss.logging.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    private static final Logger LOG = Logger.getLogger(ApplicationControllerAdvice.class);

    @ExceptionHandler(AvaliacaoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleAvaliacaoNotFoundException(AvaliacaoNotFoundException ex) {
        LOG.error(String.format("AvaliacaoNotFoundException -> %s", ex.getMessage()));
        return new ApiErrors().withError(ex.getMessage());
    }

    @ExceptionHandler(AtividadeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleAtividadeNotFoundException(AtividadeNotFoundException ex) {
        LOG.error(String.format("AtividadeNotFoundException -> %s", ex.getMessage()));
        return new ApiErrors().withError(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUserNotFoundException(UserNotFoundException ex) {
        LOG.error(String.format("UserNotFoundException -> %s", ex.getMessage()));
        return new ApiErrors().withError(ex.getMessage());
    }

    @ExceptionHandler(AvaliacaoDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleAvaliacaoDuplicatedException(AvaliacaoDuplicatedException ex) {
        LOG.error(String.format("AvaliacaoDuplicatedException -> %s", ex.getMessage()));
        return new ApiErrors().withError(ex.getMessage());
    }

    @ExceptionHandler(ExternalServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiErrors handleExternalServiceException(ExternalServiceException ex) {
        LOG.error(String.format("ExternalServiceException -> %s", ex.getMessage()));
        return new ApiErrors().withError(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrors handleDataIntegrityConstraintViolationException(DataIntegrityViolationException ex) {
        return new ApiErrors().withError(ex.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        LOG.error(String.format("HttpMessageNotReadableException -> %s", ex.getMostSpecificCause().getMessage()));
        return new ApiErrors().withError(ex.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        LOG.error(String.format("EmptyResultDataAccessException -> %s", ex.getMostSpecificCause().getMessage()));
        return new ApiErrors().withError(ex.getMostSpecificCause().getMessage());
    }
}
