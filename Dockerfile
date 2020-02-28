FROM adoptopenjdk/openjdk13
WORKDIR /usr/app
COPY ./target/Todo-Application.jar .
CMD ["java", "-jar", "Todo-Application.jar"]