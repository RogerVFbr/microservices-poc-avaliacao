package com.soundlab.template.services;

import com.soundlab.template.core.Avaliacao;
import com.soundlab.template.core.dto.AvaliacaoRequestDTO;
import com.soundlab.template.exceptions.AtividadeNotFoundException;
import com.soundlab.template.exceptions.AvaliacaoDuplicatedException;
import com.soundlab.template.exceptions.AvaliacaoNotFoundException;
import com.soundlab.template.exceptions.UserNotFoundException;
import com.soundlab.template.external.atividade.AtividadeExternalService;
import com.soundlab.template.external.user.UserExternalService;
import com.soundlab.template.repositories.AvaliacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService extends AbstractGenericService<AvaliacaoRepository, Avaliacao>{
    private AtividadeExternalService atividadeExternalService;
    private UserExternalService userExternalService;

    protected AvaliacaoService(AvaliacaoRepository repository,
                               AtividadeExternalService atividadeExternalService,
                               UserExternalService userExternalService) {
        super(repository);
        this.atividadeExternalService = atividadeExternalService;
        this.userExternalService = userExternalService;
    }

    public void save(AvaliacaoRequestDTO data) {
        if (atividadeExternalService.fetchById(data.getActivityId()) == null)
            throw new AtividadeNotFoundException(data.getActivityId());

        if (userExternalService.fetchById(data.getUserId()) == null)
            throw new UserNotFoundException(data.getUserId());

        repository.findByUserIdAndActivityId(data.getUserId(), data.getActivityId())
            .ifPresent(avaliacao -> {
                throw new AvaliacaoDuplicatedException(data.getUserId(), data.getActivityId());
            });

        repository.save(
            new Avaliacao()
                .withUserId(data.getUserId())
                .withActivityId(data.getActivityId())
        );
    }

    public void saveGrade(Long id, Integer grade) {
        Avaliacao avaliacao = repository.findById(id).orElseThrow(() -> new AvaliacaoNotFoundException(id));
        atividadeExternalService.saveGrade(avaliacao.getActivityId(), avaliacao.getUserId(), grade);
        repository.deleteById(id);
    }
}
