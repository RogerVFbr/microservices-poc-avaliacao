package com.soundlab.template.repositories;

import com.soundlab.template.core.Avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    <T> List<T> findBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
    Optional<Avaliacao> findByUserIdAndActivityId(Long userId, Long activityId);
}
