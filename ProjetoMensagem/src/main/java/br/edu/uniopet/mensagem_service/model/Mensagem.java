package br.edu.uniopet.mensagem_service.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Getter @Setter
    private Long idPessoa;

    @Getter @Setter
    @Transient
    private Pessoa pessoa;

    @NonNull
    @Getter @Setter
    private String texto;

    @NonNull
    @Getter @Setter
    private String dataEnvio;

    @NonNull
    @Getter @Setter
    private Boolean status;
}