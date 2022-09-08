#Author: Gustavo Avila #Email: gustavoaviila@hotmail.com #Tech Stack: Java 8, Spring Boot, JPA, HIBERNATE, Docker, MySQL, Jwt, Swagger #Tipo: delivery

#instruções Gerais

Os comandos apresentados são necessários para compilar o projeto e preparar/executar a imagem Docker em ambiente de desenvolvimento. Todos os comando devem ser executados via terminal diretamente no diretório raiz deste projeto. A aplicação roda no Tomcat, por tanto a porta padrão é 8080 Se necessário trocar a porta, no arquivo de configurações application.properties use o comando "server.port = {porta_desejada}" para mudar a configuração.

1 - Realizado o build do projeto A execução do build do projeto é necessária para gerar os binários da aplicação, bem como gerar os arquivos para que seja possível a construção da imagem docker: mvn clean package

2 - Construir imagem docker docker build -t delivery:v1 .

3 - Executar a imagem Docker docker-compose up -d Obs: as portas são customizaveis

4 - Depois de realizar as operações acima é possivel acessar a aplicação via browser nos seguintes PATH: localhost:8080/**
