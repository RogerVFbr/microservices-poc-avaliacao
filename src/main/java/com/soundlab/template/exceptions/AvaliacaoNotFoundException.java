package com.soundlab.template.exceptions;

public class AvaliacaoNotFoundException extends RuntimeException {
    public AvaliacaoNotFoundException(Long id) {
        super(String.format("Avaliação com id '%s' não encontrada.", id));
    }
}
