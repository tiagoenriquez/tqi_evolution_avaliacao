# TQI Evolution Avaliação 2021

API para cadastro de clientes e solicitação de empréstimo bancário escrita em Java com os recursos do framework Spring Boot.
Esta API foi inicialiazada através do Spring Initializr e utiliza as seguintes dependências:
 - Lombok: para evitar escrita de métodos comuns a classes de modelos;
 - Spring Web: para usar anotações e classes de API Rest;
 - Spring Security: para criptografar senha e autenticar os clientes
 - PostgreSQL Driver: para conexão com banco de dados PostgreSQL
 - Auth0: para autenticar os clientes com JWT
Esta API está disponível para uso por meio do endereço eletrônico https://tqi-tiago.herokuapp.com.

## Rotas

1
 - path: /api/clientes/cadastro
 - verbo: POST
 - keys (body): nome (string); email (string); cpf (string); rg (string); renda (decimal); endereco (string); senha (string)
 - descrição: o cliente se cadastra no sistema e recebe os dados do cadastro.
 - regras: não podem haver dois clientes com o mesmo "cpf", nem com o mesmo "rg".

2
 - path: /api/login
 - verbo: POST
 - keys (body): email (string); senha (string)
 - descrição: o cliente faz login no sistema e recebe um token de autenticação.
 - regras: o cliente só pode fazer login se tiver cadastro e a senha for passada corretamente.

3
 - path: /api/solicitacoes-de-emprestimo/cadastro
 - verbo: POST
 - header: Authentication: bearer <token de autenticação>
 - keys (body): valorDoEmprestimo (decimal); dataDaPrimeiraParcela (string); quantidadeDeParcelas (inteiro)
 - descrição: o cliente faz solicitação de empréstimo e recebe os dados da solicitação.
 - regras: o cliente deve estar logado, a data da primeira parcela deve estar dentro dos próximos 3 meses (90 dias) e a quantidade de parcelas é no máximo 60.

4
 - path: /api/solicitacoes-de-emprestimo/todas-do-cliente-logado
 - verbo: GET
 - header: Authentication: bearer <token de autenticação>
 - descrição: o cliente recebe a lista de solicitações de empréstimo feitas por ele com informação de id, valor do empréstimo e quantidade de parcelas.
 - regras: o cliente deve estar logado.

5
 - path: /api/solicitacoes-de-emprestimo/detalhes/<id da solicitação de empréstimo>
 - verbo: GET
 - keys (param): idCliente (inteiro)
 - header: Authentication: bearer <token de autenticação>
 - descrição: o cliente informa o seu id da solicitação de empréstimo por param e recebe todas as informações referentes a essa solicitação.
 - regras: o cliente deve estar logado e a solicitação de empréstimo deve ter sido feita por ele.
 
Boa sorte no uso desta API.
