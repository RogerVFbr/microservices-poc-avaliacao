package com.soundlab.template.exceptions;

public class AvaliacaoDuplicatedException extends RuntimeException {
    public AvaliacaoDuplicatedException(Long userId, Long activityId) {
        super(String.format("Pedido de avaliação para usuário '%s' e atividade '%s' já existente.", userId, activityId));
    }
}
