package domain.validacoes;

import domain.dto.DadosRegistro;
import domain.repositories.TopicosRepository;
import infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDuplicidade implements ValidacaoRegistro{
    @Autowired
    private TopicosRepository topicosRepository;

    @Override
    public void validar(DadosRegistro dados) {
        var topico = topicosRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if (topico != null) {
            throw new ValidacaoException("Erro ao executar. Tópico duplicado.");
        }
    }
}
