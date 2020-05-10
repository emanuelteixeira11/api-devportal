package io.api.apidevportalmanager;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.testcontainers.containers.GenericContainer;

import java.util.Collections;

@Accessors(chain = true)
@Setter
public class ArangoDBContainer {

    private static final Integer EXPOSED_PORT = 8529;
    private final GenericContainer genericContainer;
    private Integer port;
    private String password;

    public ArangoDBContainer() {
        this.genericContainer = new GenericContainer("arangodb:latest");
    }

    public void run() {
        genericContainer.addEnv("ARANGO_ROOT_PASSWORD", password);
        genericContainer.addExposedPort(EXPOSED_PORT);
        genericContainer.setPortBindings(Collections.singletonList(port + ":" + EXPOSED_PORT));
        genericContainer.start();
    }

    public void stop() {
        genericContainer.stop();
    }
}
