package domain.validacoes;

import domain.dto.DadosRegistro;
import domain.dto.DadosTopicos;
import domain.repositories.TopicosRepository;
import domain.topico.Topico;
import infra.exceptions.ValidacaoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValidacaoTopicos {
    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private List<ValidacaoRegistro> validacaoRegistroList;

    public Topico registro(@Valid DadosRegistro dados) {
        validacaoRegistroList.forEach(v -> v.validar(dados));
        var topico = new Topico(dados);
        topicosRepository.save(topico);
        return topico;
    }

    public Topico detalhesTopico(Long id) {
        if (id != null) {
            return topicosRepository.getReferenceById(id);
        } else {
            throw new ValidacaoException("Informe o ID para a busca.");
        }
    }

    public Topico atualizacao(@Valid DadosTopicos dados) {
        Optional<Topico> topico = Optional.of(topicosRepository.getReferenceById(dados.id()));

        if (topico.isPresent()) {
            var topicoAtualizado = topico.get();
            topicoAtualizado.atualizarDados(dados);
            return topicoAtualizado;
        }

        throw new ValidacaoException("Não foi possível encontrar este tópico.");
    }

    public void deletar(Long id) {
        Optional<Topico> topico = Optional.of(topicosRepository.getReferenceById(id));

        if (topico.isPresent()) {
            topicosRepository.deleteById(id);
        }
    }

    public void fechar(Long id) {
        Optional<Topico> topico = Optional.of(topicosRepository.getReferenceById(id));

        if (topico.isPresent()) {
            var topicoFechado = topico.get();
            topicoFechado.fecharTopico();
            topicosRepository.save(topicoFechado);
        }
    }
}
