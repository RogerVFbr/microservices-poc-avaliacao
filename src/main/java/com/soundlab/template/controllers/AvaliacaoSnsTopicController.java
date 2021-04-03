package com.soundlab.template.controllers;

import com.soundlab.template.core.dto.AvaliacaoRequestDTO;
import com.soundlab.template.services.AvaliacaoService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sns/avaliacao")
class AvaliacaoSnsTopicController {
    private static final Logger LOG = LogManager.getLogger(AvaliacaoSnsTopicController.class);
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoSnsTopicController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @NotificationSubscriptionMapping
    public ResponseEntity confirmSubscription(NotificationStatus status) {
        status.confirmSubscription();
        LOG.info("SNS sign up confirmed.");
        return ResponseEntity.noContent().build();
    }

    @NotificationMessageMapping
    public ResponseEntity receiveNotification(@RequestHeader("x-amz-sns-message-id") String messageId,
                                              @NotificationMessage AvaliacaoRequestDTO notification) {
        LOG.info(String.format("Topic notification received with id: %s.", messageId));
        avaliacaoService.save(notification);
        return ResponseEntity.noContent().build();
    }

    @NotificationUnsubscribeConfirmationMapping
    public ResponseEntity handleUnsubscribeMessage(NotificationStatus status) {
        status.confirmSubscription();
        LOG.error("SNS unsubscribe confirmed.");
        return ResponseEntity.noContent().build();
    }
}
