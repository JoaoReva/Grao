##Proposta
João está começando a investir e semanalmente, ele vai investir com a Grão R$ 100,00 e queremos mostrar para João quanto ele terá poupado e quanto o seu dinheiro terá rendido daqui 36 semanas. 
Para isso temos algumas regras:
 * Consideramos que a Taxa Selic seja de 4,25% ao ano
 * Pagamos 100% da Taxa Selic
 * Que o ano tem 252 dias úteis
 * Só rentabilizamos dias úteis (não se preocupe com feriados)
 * Desconsiderar Impostos sobre os investimentos

A fórmula para o cálculo da rentabilidade é ```M = P . (1 +  i) ^ t/252```

### Tecnologia
Pode utilizar qualquer linguagem de programação, porém, temos preferência pelas seguintes:
 * Kotlin
 * Java
 * Python
 * NodeJS

### Diferenciais 
 * Teste de unidade
 * Docker
 * Documentação

### Requisitos 
 * README passo-a-passo para execução
 * Prazo de 1  a 2 dias corridos.
 * Entregar um repositório público (Github, Gitlab ou Bitbucket)

##Requisitos
 * Maven 3
 * Java 1.8

##Build
 * Rodar **buildJar.bat**, será gerado o **.jar** em **target\rest-service-0.0.1-SNAPSHOT.jar**

##Iniciar serviço
 * Rodar **startServer.bat** será iniciado o servidor na porta 8080

##Como usar
 * Rodar o **buildJar.bat**
 * Rodar o **startServer.bat**
 * Realizar um requisição **post** para a url http://localhost:8080/Investment/Simulation
 * Dados da requisição: 
``` 
{
	//Valor inicial - Obrigatório
    "initialValue": number double (100.0), 
	
	//Data inicial se não preenchido ele considera a data de hoje
    "startDate": date ("2021-06-30"),
	
	//Data final - Obrigatório
    "endDate": date (2021-06-30)
}
```
 * Retorno:
``` 
{
	//Valor total investido mais juros recebidos no período
    "totalValueOnFinalDate": number double (100.0)
}
```