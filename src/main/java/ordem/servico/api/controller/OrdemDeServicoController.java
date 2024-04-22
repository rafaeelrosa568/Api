package ordem.servico.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import ordem.servico.api.domain.OrdemDeServico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "OrdemDeServico_api")
public class OrdemDeServicoController {

    @Autowired
    private OrdemDeServicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroOrdemDeServico dados, UriComponentsBuilder uriBuilder) {
        var servico = new OrdemDeServico(dados);
        repository.save(servico);

        var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoOrdemDeServico(servico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemOrdemDeServico>> listar(@PageableDefault(size = 4, sort = {"cliente"} ) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemOrdemDeServico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoOrdemDeServico dados) {
        var servico = repository.getReferenceById(dados.id());
        servico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoOrdemDeServico(servico));

    }


    // Exclusão lógica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var servico = repository.getReferenceById(id);
        servico.excluir();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var servico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoOrdemDeServico(servico));

    }



}
