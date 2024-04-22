package ordem.servico.api.domain.OrdemDeServico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroOrdemDeServico(
        @NotBlank String cliente,
        String cnpjCpf,
        @NotNull
        String telefone,
        String endereco,

        TipoServico tipoServico,
        @NotNull
        String valor,
        @NotNull
        String os) {
}
