package controller;

import domain.dto.DadosDetalhes;
import domain.dto.DadosLista;
import domain.dto.DadosRegistro;
import domain.dto.DadosTopicos;
import domain.repositories.TopicosRepository;
import domain.topico.Topico;
import domain.validacoes.ValidacaoTopicos;
import infra.security.DadosTokenJWT;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {
    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private ValidacaoTopicos validacaoTopicos;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DadosRegistro dados, UriComponentsBuilder uriBuilder) {
        var topico = validacaoTopicos.registro(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhes(topico));
    }

    @GetMapping
    public Page<DadosLista> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable pagina) {
        return topicosRepository.findAllByStatusIgnoreCase("aberto", pagina).map(DadosLista::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = validacaoTopicos.detalhesTopico(id);
        return ResponseEntity.ok(new DadosDetalhes(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosTopicos dados) {
        var topico = validacaoTopicos.atualizacao(dados);
        return ResponseEntity.ok(new DadosDetalhes(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        validacaoTopicos.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/resolver/{id}")
    @Transactional
    public ResponseEntity fechar(@PathVariable Long id) {
        validacaoTopicos.fechar(id);
        return ResponseEntity.noContent().build();
    }
}
