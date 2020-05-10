package io.api.apidevportalmanager.repositories;


import com.arangodb.springframework.repository.ArangoRepository;
import io.devportal.entities.ApiMetadata;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiMetadataRepository extends ArangoRepository<ApiMetadata, String> {
}
