package io.api.apidevportalmanager.configurations.db;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("arangoDbConfig")
@EnableArangoRepositories(basePackages = {"io.api.apidevportalmanager.repositories"})
@ConfigurationProperties
@Slf4j
public class ArangoDBConfiguration extends AbstractArangoConfiguration {

    @Value("${spring.data.arangodb.database}")
    private String database;

    @Value("${spring.data.arangodb.host}")
    private String host;

    @Value("${spring.data.arangodb.port}")
    private Integer port;

    @Value("${spring.data.arangodb.user}")
    private String user;

    @Value("${spring.data.arangodb.password}")
    private String password;

    @Override
    public ArangoDB.Builder arango() {
        log.info("Connecting to ArangoDb. host:{}, port:{}", host, port);
        return new ArangoDB.Builder().host(host, port).user(user).password(password);
    }

    @Override
    public String database() {
        return database;
    }
}
