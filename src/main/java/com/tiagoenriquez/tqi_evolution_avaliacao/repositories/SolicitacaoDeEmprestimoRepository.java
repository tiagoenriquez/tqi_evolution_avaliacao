package com.tiagoenriquez.tqi_evolution_avaliacao.repositories;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface responsável por persistir e recuperar dados de solicitações de empréstimo no banco de dados.
 */
public interface SolicitacaoDeEmprestimoRepository extends JpaRepository<SolicitacaoDeEmprestimo, Integer> {

    /**
     * Recebe um id de cliente e retorna do banco de dados todas as solicitações de empréstimo feitas por ele, com
     * informação de id, valor do empréstimo e quantidade de parcelas.
     * @param idCliente
     * @return
     */
    @Query(value = "select id_solicitacao_de_emprestimo, valor_do_emprestimo, quantidade_de_parcelas from solicitacoes_de_emprestimo where id_cliente = ?1", nativeQuery = true)
    List<Object> findAllByCliente(final Integer idCliente);

}
