#Author: Gustavo Avila #Email: gustavoaviila@hotmail.com #Tech Stack: Java 11, Spring Boot, JPA, HIBERNATE, Docker, Postgres #Tipo: challenge

#instruções Gerais

Os comandos apresentados são necessários para compilar o projeto e preparar/executar a imagem Docker em ambiente de desenvolvimento. Todos os comando devem ser executados via terminal diretamente no diretório raiz deste projeto. A aplicação roda no Tomcat, por tanto a porta padrão é 8080 Se necessário trocar a porta, no arquivo de configurações application.properties use o comando "server.port = {porta_desejada}" para mudar a configuração.

1 - Realizado o build do projeto A execução do build do projeto é necessária para gerar os binários da aplicação, bem como gerar os arquivos para que seja possível a construção da imagem docker: mvn package

2 - Construir imagem docker docker build -t challenge:v1 .

3 - Executar a imagem Docker docker-compose up -d Obs: as portas são customizaveis

4 - Depois de realizar as operações acima é possivel acessar a aplicação via browser nos seguintes PATH: localhost:8080/**

5 - Exemplos de utilização da API: { "info": { "_postman_id": "a152515b-25f8-4113-830d-92287e7b36ff", "name": "Vote-api", "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json" }, "item": [ { "name": "Schedule", "item": [ { "name": "Create Schedule", "request": { "method": "POST", "header": [], "body": { "mode": "raw", "raw": "{\r\n "name": "governo"\r\n}", "options": { "raw": { "language": "json" } } }, "url": { "raw": "localhost:8080/schedules", "host": [ "localhost" ], "port": "8080", "path": [ "schedules" ] } }, "response": [] }, { "name": "Find Schedule By Id", "request": { "method": "GET", "header": [], "url": { "raw": "localhost:8080/schedules/1", "host": [ "localhost" ], "port": "8080", "path": [ "schedules", "1" ] } }, "response": [] }, { "name": "Find Schedule By Name", "request": { "method": "GET", "header": [], "url": { "raw": "localhost:8080/schedules/teste", "host": [ "localhost" ], "port": "8080", "path": [ "schedules", "teste" ] } }, "response": [] } ] }, { "name": "Session", "item": [ { "name": "Create Session", "request": { "method": "POST", "header": [], "url": { "raw": "localhost:8080/sessions", "host": [ "localhost" ], "port": "8080", "path": [ "sessions" ] } }, "response": [] } ] }, { "name": "Vote", "item": [ { "name": "Create Vote", "request": { "method": "POST", "header": [], "body": { "mode": "raw", "raw": "{\r\n "cpf" : "46226647809",\r\n "vote": "true",\r\n "idSession": "1",\r\n "idSchedule": "1"\r\n}", "options": { "raw": { "language": "json" } } }, "url": { "raw": "localhost:8080/votes", "host": [ "localhost" ], "port": "8080", "path": [ "votes" ] } }, "response": [] }, { "name": "Find Votes", "request": { "method": "GET", "header": [], "url": null }, "response": [] }, { "name": "Verify Cpf", "request": { "method": "GET", "header": [], "url": null }, "response": [] } ] } ] }
