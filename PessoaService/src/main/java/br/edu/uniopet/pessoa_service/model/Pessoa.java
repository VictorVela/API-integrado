package br.edu.uniopet.pessoa_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    private Long idEndereco;

    @NonNull
    @Getter @Setter
    @Transient
    private Endereco endereco;

    @NonNull
    @Getter @Setter
    private String nome;

    @NonNull
    @Getter @Setter
    private Integer idade;

    @NonNull
    @Getter @Setter
    private Character sexo;
}
