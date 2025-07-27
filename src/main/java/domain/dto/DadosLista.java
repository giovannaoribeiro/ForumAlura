package domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import domain.topico.Topico;

import java.time.LocalDateTime;

public record DadosLista (
        Long id,
        String titulo,
        String mensagem,

        @JsonFormat (pattern = "dd/mm/yyyy HH:mm")
        LocalDateTime data,
        String status,
        String autor,
        String curso
) {
    public DadosLista(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
