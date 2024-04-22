package ordem.servico.api.domain.OrdemDeServico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoOrdemDeServico(
        @NotNull
        Long id,
        String cliente,
        String cnpjCpf,
        String telefone,
        String endereco,
        String valor,
        String os) {

}
