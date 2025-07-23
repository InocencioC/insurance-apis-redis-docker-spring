# Estágio 1: Build da Aplicação
FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

# Instala o Maven no contêiner
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Copia o pom.xml principal do seu projeto Spring Boot (que está em apis/)
COPY pom.xml .

# Copia a pasta .mvn, SE ELA REALMENTE ESTIVER DENTRO da pasta 'apis'
# Se o seu projeto USA Maven Wrapper e o diretório .mvn ESTÁ DENTRO de `apis`:
COPY .mvn ./.mvn


# Copia o código fonte da sua aplicação
# A pasta src está diretamente em 'apis/', então é apenas 'src'
COPY src src/

# Empacota a aplicação Spring Boot
RUN mvn clean package -DskipTests

# Estágio 2: Criação da Imagem Final (Lightweight)
FROM eclipse-temurin:17-jre-jammy

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR executável do estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que sua aplicação Spring Boot usa
EXPOSE 8080

# Comando para rodar a aplicação quando o contêiner for iniciado
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]