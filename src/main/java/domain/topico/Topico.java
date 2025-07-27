package domain.topico;

import domain.dto.DadosRegistro;
import domain.dto.DadosTopicos;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    private String autor;
    private String curso;

    public Topico(@Valid DadosRegistro dados) {
        this.status = "Aberto";
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atualizarDados(@Valid DadosTopicos dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
    }

    public void fecharTopico() {
        this.status = "Fechado";
    }
}
