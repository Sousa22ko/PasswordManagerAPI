# PasswordManagerAPI

Api desenvolvida para o projeto do processo seletivo da PariPassu.

## sobre o sistema
O sistema foi desenvolvido usando openJdk11.
Ele provê todas as funcionalidades necessárias de backend do projeto.

## Como executar
Para executar é preciso criar um banco de dados postgres

Para isso basta abrir o terminal do PSQL e inserir o codigo :
```sql
create database pswman;
```

O projeto usa a ferramenta de migração flyway, logo as tabelas nescessárias serão geradas automaticamente.

Basta executar o projeto pela ide (eclipse ou intelijj) ou pelo terminar com o comando:
```
mvn spring-boot:run
```
