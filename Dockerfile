FROM defsloc.azurecr.io/advisingbank/mymavenimage:latest

EXPOSE 7500

COPY ./target/my-app-1.0-SNAPSHOT*.jar /usr/app/
WORKDIR /usr/app

CMD java -jar my-app-1.0-SNAPSHOT*.jar
