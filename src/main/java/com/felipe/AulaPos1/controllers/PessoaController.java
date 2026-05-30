package com.felipe.AulaPos1.controllers;

import com.felipe.AulaPos1.models.Pessoa;
import com.felipe.AulaPos1.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> getPessoas()
    {
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> getPessoa(@PathVariable Long id) {
        try
        {
            return pessoaRepository.findById(id);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa adicionar(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    void deletePessoa(@PathVariable Long id) {
        pessoaRepository.deleteById(id);
    }

    // Professor, como a classe Pessoa so possui id e nome, os métodos PUT e PATCH ficariam iguais.
    // por conta disso, optei por seguir a orientação anterior da atividade, PUT altera tudo, e PATCH focado em
    // atualização parcial, somente diferenciar os dois.

    @PutMapping("/{id}")
    Pessoa updatePessoa(@RequestBody Pessoa novaPessoa, @PathVariable Long id) {
        Pessoa p = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa não encontrada"
                ));

        p.setId(novaPessoa.getId());
        p.setNome(novaPessoa.getNome());
        return pessoaRepository.save(p);
    }

    @PatchMapping("/{id}")
    public Pessoa patchPessoa(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        Pessoa p = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pessoa não encontrada"
                ));
        if (novaPessoa.getId() != null) {
            p.setId(novaPessoa.getId());
        }
        if (novaPessoa.getNome() != null) {
            p.setNome(novaPessoa.getNome());
        }
        return pessoaRepository.save(p);
    }
}