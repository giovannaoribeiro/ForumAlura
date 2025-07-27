package domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRegistro(
        @NotBlank (message = "Não é possível salvar sem título.")
        String titulo,

        @NotBlank (message = "Não é possível salvar sem mensagem.")
        String mensagem,

        @NotBlank (message = "Não é possível salvar sem autor.")
        String autor,

        @NotBlank (message = "Não é possível salvar sem curso.")
        String curso
) {
}
