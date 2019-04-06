package br.edu.uniopet.pessoa_service.service;

import br.edu.uniopet.pessoa_service.model.Endereco;
import br.edu.uniopet.pessoa_service.model.Pessoa;
import br.edu.uniopet.pessoa_service.repository.PessoaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    RestTemplate restTmplt = new RestTemplate();

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).get();

        Endereco endereco = restTmplt.getForObject("http://172.31.0.122:9998/enderecos/" + pessoa.getIdEndereco(), Endereco.class);
        pessoa.setEndereco(endereco);

        return pessoa;
    }

    public String saveOrUpdate(Pessoa pessoa) {

        if (pessoa.getId() != null) {
            pessoaRepository.save(pessoa);
            return "Dados da pessoa atualizados com sucesso!";
        }

        HttpHeaders header = new HttpHeaders();

        header.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> bodyParamMap = new HashMap<String, String>();

        bodyParamMap.put("pais", pessoa.getEndereco().getPais());
        bodyParamMap.put("estado", pessoa.getEndereco().getEstado());
        bodyParamMap.put("cidade", pessoa.getEndereco().getCidade());
        bodyParamMap.put("bairro", pessoa.getEndereco().getBairro());

        String reqBodyData = null;
        try {
            reqBodyData = new ObjectMapper().writeValueAsString(bodyParamMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity<String> requestEnty = new HttpEntity<>(reqBodyData, header);
        ResponseEntity<Object> result = restTmplt.postForEntity("http://172.31.0.122:9998/enderecos", requestEnty, Object.class);

        pessoa.setIdEndereco(Long.parseLong(result.getBody().toString()));

        pessoaRepository.save(pessoa);

        return "Pessoa criada com sucesso";
    }

    public String deleteById(Pessoa pessoa) {
        pessoaRepository.deleteById(pessoa.getId());

        if (!pessoaRepository.existsById(pessoa.getId())) {
            return "Deletado com sucesso";
        }

        return "Erro!!!!";
    }

}
