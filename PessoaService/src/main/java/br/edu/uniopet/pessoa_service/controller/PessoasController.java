package br.edu.uniopet.pessoa_service.controller;

import br.edu.uniopet.pessoa_service.model.Pessoa;
import br.edu.uniopet.pessoa_service.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoasController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> findAll(){
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable Long id){
        return pessoaService.findById(id);
    }

    @PostMapping
    public String save(@RequestBody Pessoa pessoa){
        return pessoaService.saveOrUpdate(pessoa);
    }
}
