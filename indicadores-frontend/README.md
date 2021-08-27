## Projeto Indicadores de probreza mundial

Frontend desenvolvido em Angular 10. Backend utiliza Java 8 & Spring
Trata-se de uma lista de países, na qual o usuário seleciona um país e 
pode ver detalhes dos indicadores.

###### A lista de países, por ser quase 'imutável' coloquei estatica, carregando no inicio da aplicação.
###### Os indicadores são consultados por demanda, usando o client do serviço para acessar.

#### SITE_URL

default: `http://localhost:4200`</br>

### Como rodar

- Backend: mvn spring-boot:run
- Frontend: npm install, npm start

## Exemplo de chamada dos serviços

- [http://localhost:8180/api/v1/indicadores/CA](http://localhost:8180/api/v1/indicadores/CA)
