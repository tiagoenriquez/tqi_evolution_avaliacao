package com.tiagoenriquez.tqi_evolution_avaliacao.services;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimo;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimoDTO;
import com.tiagoenriquez.tqi_evolution_avaliacao.repositories.SolicitacaoDeEmprestimoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SolicitacaoDeEmprestimoService {

    private final SolicitacaoDeEmprestimoRepository solicitacaoDeEmprestimoRepository;
    private final ClienteService clienteService;

    public SolicitacaoDeEmprestimoService(SolicitacaoDeEmprestimoRepository solicitacaoDeEmprestimoRepository,
                                          ClienteService clienteService) {
        this.solicitacaoDeEmprestimoRepository = solicitacaoDeEmprestimoRepository;
        this.clienteService = clienteService;
    }

    /**
     * Verifica se a data da primeira parcela entá dentro dos próximos 3 meses.
     * @param primeiraParcela
     * @return
     * @throws Exception
     */
    private LocalDate validarPrimeiraParcela(LocalDate primeiraParcela) throws Exception {
        if(LocalDate.now().plusDays(90).isBefore(primeiraParcela)) {
            throw new Exception("Data da primeira parcela precisa ser nos próximos 3 meses.");
        }
        return primeiraParcela;
    }

    /**
     * Verifica se a quantidade de parcelas é menor do que 60.
     * @param parcelas
     * @return
     * @throws Exception
     */
    private Integer validarNParcelas(Integer parcelas) throws Exception {
        if(parcelas > 60) {
            throw new Exception("Número máximo de parcelas é 60");
        }
        return parcelas;
    }

    /**
     * Insere uma solicitação de empréstimo no banco de dados.
     * @param solicitacaoDeEmprestimo
     * @return
     * @throws Exception
     */
    public ResponseEntity<SolicitacaoDeEmprestimo> inserir(SolicitacaoDeEmprestimoDTO dto) throws Exception {
        try {
            SolicitacaoDeEmprestimo solicitacaoDeEmprestimo = new SolicitacaoDeEmprestimo();
            solicitacaoDeEmprestimo.setValorDoEmprestimo(dto.getValorDoEmprestimo());
            solicitacaoDeEmprestimo.setDataDaPrimeiraParcela(validarPrimeiraParcela(dto.getDataDaPrimeiraParcela()));
            solicitacaoDeEmprestimo.setQuantidadeDeParcelas(validarNParcelas(dto.getQuantidadeDeParcelas()));
            Cliente cliente = clienteService.clienteLogado();
            solicitacaoDeEmprestimo.setIdCliente(cliente.getIdCliente());
            solicitacaoDeEmprestimo.setCliente(cliente);
            return ResponseEntity.ok(solicitacaoDeEmprestimoRepository.save(solicitacaoDeEmprestimo));
        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

}
