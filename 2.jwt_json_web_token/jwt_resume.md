************** PRECISA FINALIZAR *******************

# JWT - JSON Web Token




## Diferenca entre authentication and authorization

- Authentication --> Verifica se as credenciais informadas estão corretas e existem no banco de dados.

- Authorization --> Aqui o usuario está autenticado, porém é verificado se ele possui as role/cargo/authority necessária para acessar determinado endpoint.


## Passo a passo de uma autenticacao por token

1.Cadastro de Usuário:
        O cliente envia uma solicitação POST para criar um novo usuário, fornecendo o nome de usuário e a senha.
        O servidor salva o novo usuário no banco de dados.


2.Obtenção do Token (Autenticação):
        Para conseguir acessar as rotas protegidas, o cliente precisa antes acessar uma outra rota POST, passando suas credenciais, para gerar o token de acesso. O servidor verifica as credenciais, e se forem válidas, gera um token JWT e o envia de volta para o cliente.
        

3.Acesso às Rotas Protegidas:
        O cliente, a partir desse ponto, inclui o token JWT no header/cabeçalho de todas as solicitações futuras para rotas protegidas.
        O servidor verifica a validade do token em cada solicitação e autoriza ou nega o acesso com base na presença e validade do token.





## Entendendo o token JWT
O token JWT é uma string complexa e codificada, dividida em três partes principais:

	- Header(cabecalho) -->  informamos o algoritmo que será utilizado para gerar o token e o tipo do token (JWT).

{
  "alg": "HS256",
  "typ": "JWT"
}


	- Payload (carga) --> Armazena informações do usuário autenticado(id, username, cargos, tempo de expiracao do token, etc...) e permite, por exemplo, retornar dados do banco relacionados a esse usuário.


{
  "sub": "1234567890",
  "name": "gabriel tal",
  "iat": 1516239022
}


	- Signature (assinatura) --> A assinatura é um hash composto do header + payload + chave secreta, que apenas nosso servidor conhece. Ele é um hash.


HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret_que_vamos_criar
)




### Resumindo:
    - Header (Cabeçalho): Contém o algoritmo de token e o tipo (JWT).

    - Payload (Carga): Armazena informações do usuário autenticado e permite, por exemplo, retornar dados do banco relacionados a esse usuário.

    - Signature (Assinatura): É um hash que proporciona segurança ao token. Não pode ser decodificado facilmente, garantindo a integridade e autenticidade do token.

    :bulb: Tanto o cabeçalho quanto a carga podem ser decodificados (Base64), mas a assinatura, que é a chave de segurança, não pode.



## Maos na maça

1. Criar uma entidade que vai implements "UserDetails". Ela vai representar os usuários autenticados. O nome da table no postgres nao pode ser "user". Coloque o nome de "users".



1. Criar os Dtos
	- Dto para cadastrar um novo usuario (username, email, password)
	- Dto para se autenticar (email and password)
	- Dto para gerar o token (String token)



1. Crie uma class "JwtService" para gerar o token. Aqui, vamos criar um attribute que será o nosso secret lá do jwt, em signature.

Lembra que lá no signature do jwt, precisamos informar um secret??  É aqui que vmaos criá-lo.

Aqui vamos ter todos os methods relacionados ao token, como por exemplo:
	- Definir o attribute que será o secret do signature do JWT token
	- Method para criar o token
	- Method para retornar esse token




1. Criar uma class para colocar o filtro, essa class vai extends GenericFilterBean

1. Uma class Service que vai implements "UserDetailsService". Aqui que vamos ter o method "loadUserByUsername()" que vai fazer a validação das credenciais informadas. Alem desse method implementado, aqui vamos ter o method para cadastrar usuario:
	- method para cadastrar usuario (verificar se ele ja existe, etc...)



1. Criar uma variable de ambiente para armazenar o secret do token. Usando a annotation @Value do org.




########## diferenca de stateless and stateful



var token = new UsernamePasswordAuthenticationToken(usuarioINformadoNoBody, senhaInformadoNoBody)
var autenticaaoQUeVamosUser = this.authentication.authenticate();



