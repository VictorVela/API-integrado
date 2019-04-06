package br.edu.uniopet.mensagem_service.service;

import br.edu.uniopet.mensagem_service.model.Mensagem;
import br.edu.uniopet.mensagem_service.model.Pessoa;
import br.edu.uniopet.mensagem_service.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MensagemService {

    @Autowired
    MensagemRepository mensagemRepository;
    RestTemplate rest = new RestTemplate();

    public List<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    public Mensagem findById(Long id) {

        Mensagem mensagem = mensagemRepository.findById(id).get();

        Pessoa pessoa = rest.getForObject("http://172.31.0.207:9998/pessoa/" + mensagem.getIdPessoa(), Pessoa.class);
        mensagem.setPessoa(pessoa);
        return mensagem;
    }

    public String saveOrUpdate(Mensagem mensagem) {
       mensagemRepository.save(mensagem);
       return "Mensagem enviada com sucesso!";
    }

    public String deleteById(Long id) {
        mensagemRepository.deleteById(id);

        if(!mensagemRepository.existsById(id)) {
            return "Deletado com sucesso!";
        }

        return "Erro ao deleter mensagem.";
    }
}