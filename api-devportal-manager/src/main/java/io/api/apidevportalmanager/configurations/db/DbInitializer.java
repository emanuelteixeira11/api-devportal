package io.api.apidevportalmanager.configurations.db;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
@Slf4j
public class DbInitializer {

    @Autowired
    private ArangoDBConfiguration arangoDBConfiguration;

    @PostConstruct
    private void initDatabase() {
        String targetDabase = arangoDBConfiguration.database();
        ArangoDatabase arangoDatabase = null;
        ArangoDB arangoDB = arangoDBConfiguration.arango().build();
        Collection<String> databases = arangoDB.getDatabases();

        if (!databases.contains(targetDabase)) {
            log.info("Database {} doesn't exists. Creating required database!", targetDabase);
            boolean creationStatus = arangoDB.createDatabase(targetDabase);
            if (creationStatus) {
                log.info("Database {} created!", targetDabase);
                arangoDatabase = arangoDB.db(targetDabase);
            } else {
                log.error("Error creating {} database! Please check arangoDB.", targetDabase);
            }
        }
    }
}
