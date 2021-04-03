package com.soundlab.template.external.atividade;

import com.soundlab.template.external.AbstractExternalHttpService;
import com.soundlab.template.external.atividade.models.AtividadeResponseDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeExternalService extends AbstractExternalHttpService<IAtividadeApi> {

    protected AtividadeExternalService(@Value("${external.atividade.base-url}") String baseUrl) {
        super(baseUrl);
    }

    public List<AtividadeResponseDTO> fetchAll() {
        return call(api.getAtividades());
    }

    public AtividadeResponseDTO fetchById(Long id) {
        return call(api.getAtividadeById(id));
    }

    public void saveGrade(Long activityId, Long userId, Integer grade) {
        callVoid(api.saveGrade(activityId, userId, grade));
    }
}
