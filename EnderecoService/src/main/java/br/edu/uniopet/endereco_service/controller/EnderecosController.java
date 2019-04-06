package br.edu.uniopet.endereco_service.controller;

import br.edu.uniopet.endereco_service.model.Endereco;
import br.edu.uniopet.endereco_service.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> findAll(){
        return  enderecoService.findAll();
    }

    @GetMapping("/{id}")
    public  Endereco findById(@PathVariable Long id){
        return  enderecoService.findById(id);
    }

    @PostMapping
    public Long create (@RequestBody Endereco endereco){
        return enderecoService.saveOrUpdadte(endereco);
    }
}
