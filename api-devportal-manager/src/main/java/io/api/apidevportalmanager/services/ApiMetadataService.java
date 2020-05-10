package io.api.apidevportalmanager.services;

import io.api.apidevportalmanager.repositories.ApiMetadataRepository;
import io.devportal.entities.ApiMetadata;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiMetadataService {

    private final ApiMetadataRepository apiMetadataRepository;
    private final Scheduler scheduler;

    public Flowable<ApiMetadata> findAll() {
        return Flowable.fromIterable(apiMetadataRepository.findAll())
                .subscribeOn(scheduler);
    }

    public Maybe<ApiMetadata> findById(String apiMetadataId) {
        Optional<ApiMetadata> apiMetadata = apiMetadataRepository.findById(apiMetadataId);
        return (apiMetadata.isPresent() ? Maybe.just(apiMetadata.get()) : Maybe.<ApiMetadata>empty()).subscribeOn(scheduler);
    }

    public Maybe<ApiMetadata> publishApi(ApiMetadata apiMetadata) {
        ApiMetadata publishedApiMetadata = apiMetadataRepository.save(apiMetadata);
        log.debug("Published new api into api repository. Service name: {}", apiMetadata.getName());
        return Maybe.just(publishedApiMetadata).subscribeOn(scheduler);
    }

    @Async
    public void deleteAll() {
        apiMetadataRepository.deleteAll();
    }

    @Async
    public void deleteById(String metadataId) {
        apiMetadataRepository.deleteById(metadataId);
    }
}
