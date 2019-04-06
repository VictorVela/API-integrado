package br.edu.uniopet.endereco_service.service;

import br.edu.uniopet.endereco_service.model.Endereco;
import br.edu.uniopet.endereco_service.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Endereco> findAll(){
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id){
        return enderecoRepository.findById(id).get();
    }

    public Long saveOrUpdadte(Endereco endereco){
        if (endereco.getId() != null){
           enderecoRepository.save(endereco);
           return endereco.getId();
        }

        enderecoRepository.save(endereco);
        return endereco.getId();
    }

    public String deleteById(Endereco endereco){
        enderecoRepository.deleteById(endereco.getId());

        if (!enderecoRepository.existsById(endereco.getId())){
            return "Deletado com sucesso";
        }

        return "Erro ao deletar";
    }
}
