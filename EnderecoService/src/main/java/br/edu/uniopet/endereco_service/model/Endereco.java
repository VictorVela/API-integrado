package br.edu.uniopet.endereco_service.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name ="ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Getter @Setter
    private String pais;

    @NonNull
    @Getter @Setter
    private String estado;

    @NonNull
    @Getter @Setter
    private String cidade;

    @NonNull
    @Getter @Setter
    private String bairro;

}
