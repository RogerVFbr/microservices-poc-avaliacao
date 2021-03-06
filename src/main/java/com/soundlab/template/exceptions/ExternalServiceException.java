package com.soundlab.template.exceptions;

public class ExternalServiceException extends RuntimeException{
    public ExternalServiceException(String serviceName, String errorBody) {
        super(String.format("Erro no serviço externo '%s' -> %s", serviceName, errorBody));
    }
}
