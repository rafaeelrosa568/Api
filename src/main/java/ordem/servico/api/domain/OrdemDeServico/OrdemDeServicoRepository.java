package ordem.servico.api.domain.OrdemDeServico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {
    Page<OrdemDeServico> findAllByAtivoTrue(Pageable paginacao);
}
