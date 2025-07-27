package domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.topico.Topico;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhes (
        @NotNull (message = "Digite número ID válido.")
        Long id,
        String titulo,
        String mensagem,
        String autor,

        @JsonFormat (pattern = "dd/mm/yyyy HH:mm")
        LocalDateTime dataCriacao,
        String status,
        String curso
) {
    public DadosDetalhes(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso());
    }
}
