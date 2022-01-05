package com.tiagoenriquez.tqi_evolution_avaliacao.repositories;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitacaoDeEmprestimoRepository extends JpaRepository<SolicitacaoDeEmprestimo, Integer> {

    @Query(value = "select id_solicitacao_de_emprestimo, valor_do_emprestimo, quantidade_de_parcelas from solicitacoes_de_emprestimo where id_cliente = ?1", nativeQuery = true)
    List<Object> findAllByCliente(final Integer idCliente);

}
