FROM openjdk:11
VOLUME /tmp
EXPOSE 8110
ADD ./target/ms-transaction-saving-account-0.0.1-SNAPSHOT.jar ms-transaction-saving-account.jar
ENTRYPOINT ["java","-jar","ms-transaction-saving-account.jar"]