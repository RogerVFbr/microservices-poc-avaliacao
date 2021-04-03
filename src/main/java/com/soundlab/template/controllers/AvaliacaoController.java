package com.soundlab.template.controllers;

import com.soundlab.template.core.Avaliacao;
import com.soundlab.template.services.AvaliacaoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(
    name = "Domain: Avaliacao",
    description = "Gerencia o domínio 'Avaliacao'."
)
@RequestMapping("avaliacao")
public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(OK)
    List<Avaliacao> findAll() {
        return avaliacaoService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, params = { "id", "grade" }, produces = { "application/json"})
    @ResponseStatus(OK)
    void saveGrade(@RequestParam("id") Long id,
                   @RequestParam("grade") Integer grade) {
        avaliacaoService.saveGrade(id, grade);
    }
}
