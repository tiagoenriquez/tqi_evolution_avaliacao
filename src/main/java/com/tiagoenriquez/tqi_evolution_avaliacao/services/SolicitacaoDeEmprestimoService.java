package com.tiagoenriquez.tqi_evolution_avaliacao.services;

import com.tiagoenriquez.tqi_evolution_avaliacao.models.Cliente;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimo;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimoDTOCadastro;
import com.tiagoenriquez.tqi_evolution_avaliacao.models.SolicitacaoDeEmprestimoDTOListagem;
import com.tiagoenriquez.tqi_evolution_avaliacao.repositories.SolicitacaoDeEmprestimoRepository;
import lombok.val;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * Recebe informações de uma solicitação de empréstimo da classe controlodara, manda algumas delas para validação,
     * cria uma solicitação de empréstimo completa com dados do usuário, salva e retorna essa solicitação.
     * @param dto
     * @return
     * @throws Exception
     */
    public ResponseEntity<SolicitacaoDeEmprestimo> inserir(SolicitacaoDeEmprestimoDTOCadastro dto) throws Exception {
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

    /**
     * Retorna todas as solicitações de empréstimo feitas pelo cliente logado.
     * @return
     * @throws Exception
     */
    public ResponseEntity<List<SolicitacaoDeEmprestimoDTOListagem>> listar() throws Exception {
        try {
            Cliente cliente = clienteService.clienteLogado();
            List<Object> objetos = solicitacaoDeEmprestimoRepository.findAllByCliente(cliente.getIdCliente());
            List<SolicitacaoDeEmprestimoDTOListagem> solicitacoes = new ArrayList<>();
            for (Object objeto : objetos) {
                Object[] obj = (Object[]) objeto;
                solicitacoes.add(new SolicitacaoDeEmprestimoDTOListagem((Integer) obj[0], (BigDecimal) obj[1], (Integer) obj[2]));
            }
            return ResponseEntity.ok(solicitacoes);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    /**
     * Recebe um id de solicitação de empréstimo e retorna os detalhes dessa solicitação.
     * @param id
     * @return
     * @throws Exception
     */
    public ResponseEntity<SolicitacaoDeEmprestimo> mostrarUma(Integer id) throws Exception {
        try {
            Optional<SolicitacaoDeEmprestimo> optional = solicitacaoDeEmprestimoRepository.findById(id);
            Cliente cliente = clienteService.clienteLogado();
            SolicitacaoDeEmprestimo solicitacaoDeEmprestimo = optional.get();
            if (solicitacaoDeEmprestimo.getIdCliente() == cliente.getIdCliente()) {
                return ResponseEntity.ok(solicitacaoDeEmprestimo);
            } else {
                throw new Exception("A solicitação de empréstimo não foi feita pelo cliente logado.");
            }
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

}
