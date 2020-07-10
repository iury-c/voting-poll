# voting-poll

Aplicativo para suportar votações de pautas online.

## Run

Para rodar o projeto por favor utilizar o comando abaixo:
```
> gradlew bootRun
```

## Test Automation

Para rodar os tests automatizados e todas as ferramentas de verificação de qualidade de código ([PMD](https://pmd.github.io/) e [Jacoco](https://www.eclemma.org/jacoco/)), rodar o comando abaixo:
```
> gradlew check
```

Todos os endpoints tem tests automatizados que rodam ao utilizar o comando acima.

## API Documentation

Para documentação foi escolhido o [Swagger 2](https://swagger.io/), através do [Spring Fox](https://springfox.github.io/springfox/), que tem integração facilitada com o [Spring Boot](https://spring.io/projects/spring-boot). Com ele pode-se manter a documentação atrelada ao próprio código, facilitando a manutenção. Para acessar a documentação do projeto, acesse o contexto abaixo com a aplicação rodando.
```
> http://localhost:8080/swagger-ui.html
```

## Database Integration

O banco de dados escolhido para o projeto foi o [H2](https://www.h2database.com/html/main.html), devido a facilidade no desenvolvimento e integração com o Spring, além de ter flexiblidade de poder ser utlizado em memória ou com persistencia em arquivo. 

Para acessar a interface do [H2](https://www.h2database.com/html/main.html) que pode ser utilizada para execução e visualização de comandos SQL acesse o contexto abaixo com o projeto rodando:
1. Entrar em `http://localhost:8080/h2-console`.
2. Colocar `password` como password.
3. Clicar em `Connect`.

## Technical Background

Além da utilização de tecnologias ligadas ao Spring Framework ([Spring Boot](https://spring.io/projects/spring-boot), [Spring JPA](https://spring.io/projects/spring-data-jpa), etc), seguem algumas outras ferramentas adicionadas ao projeto:

* **[PMD](https://pmd.github.io/)** - para análise de qualidade estática de código.
* **[Jacoco](https://www.eclemma.org/jacoco/)** - para análise de cobertura de testes automatizados no código.
* **[Lombok](https://projectlombok.org/)** - para evitar escrita desnecessária de codigo e deixar a legibilidade mais clara. 