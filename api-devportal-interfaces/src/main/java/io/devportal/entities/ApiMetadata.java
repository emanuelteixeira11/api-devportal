package io.devportal.entities;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import io.api.apidevportalmanager.entities.dao.Pom;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Document("apiMetadata")
@Accessors(chain = true)
public class ApiMetadata {

    @ArangoId
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private Collection<ApiServiceTag> tags;

    @Field("implementations")
    private Collection<ApiServiceMetadata> apiServiceMetadata;

    @Data
    public class ApiServiceMetadata {

        private String version;

        private Pom pomDetails;
    }

    @Data
    public class ApiServiceTag {

        private String tag;

    }
}
