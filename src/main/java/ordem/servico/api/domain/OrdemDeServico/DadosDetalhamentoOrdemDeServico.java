package ordem.servico.api.domain.OrdemDeServico;

public record DadosDetalhamentoOrdemDeServico(Long id, String cliente, String cnpjCpf, String telefone, String endereco, TipoServico tipoServico, String valor, String os) {

    public DadosDetalhamentoOrdemDeServico(OrdemDeServico ordemDeServico) {
        this(ordemDeServico.getId(), ordemDeServico.getCliente(), ordemDeServico.getCnpjCpf(), ordemDeServico.getTelefone(), ordemDeServico.getEndereco(), ordemDeServico.getTipoServico(), ordemDeServico.getValor(), ordemDeServico.getOs());
    }
}
