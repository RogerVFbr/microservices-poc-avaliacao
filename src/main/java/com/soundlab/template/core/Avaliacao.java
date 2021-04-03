package com.soundlab.template.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "tb_avaliacoes")
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({ "id", "activityId", "userId", "dateCreated", "dateModified" })
public class Avaliacao extends AbstractAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "user_id")
    private Long userId;

    public Avaliacao withActivityId(Long id) {
        this.activityId = id;
        return this;
    }

    public Avaliacao withUserId(Long id) {
        this.userId = id;
        return this;
    }
}
