package ordem.servico.api.domain.OrdemDeServico;

public record DadosListagemOrdemDeServico(Long id, String cliente, TipoServico tipoServico, String Valor, String os) {

    public DadosListagemOrdemDeServico(OrdemDeServico ordemDeServico){
        this(ordemDeServico.getId(), ordemDeServico.getCliente(), ordemDeServico.getTipoServico(), ordemDeServico.getValor(), ordemDeServico.getOs());
    }
}
