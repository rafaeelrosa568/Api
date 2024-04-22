package ordem.servico.api.domain.OrdemDeServico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "servicos")
@Entity(name = "OrdemDeServico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrdemDeServico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cliente;
    private String cnpjCpf;
    private String telefone;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;
    private String valor;
    private String os;
    private boolean ativo;

    public OrdemDeServico(DadosCadastroOrdemDeServico dados) {
        this.ativo = true;
        this.cliente = dados.cliente();
        this.cnpjCpf = dados.cnpjCpf();
        this.telefone = dados.telefone();
        this.endereco = dados.endereco();
        this.tipoServico = dados.tipoServico();
        this.valor = dados.valor();
        this.os = dados.os();


    }

    public void atualizarInformacoes(DadosAtualizacaoOrdemDeServico dados) {
        if (dados.cliente() != null) {
            this.cliente = dados.cliente();
        }
        if (dados.cnpjCpf() != null) {
            this.cnpjCpf = dados.cnpjCpf();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco = dados.endereco();
        }
        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
        if (dados.os() != null) {
            this.os = dados.os();
        }



    }

    public void excluir() {
        this.ativo = false;
    }
}
