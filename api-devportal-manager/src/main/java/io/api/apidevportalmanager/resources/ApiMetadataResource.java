package io.api.apidevportalmanager.resources;

import io.api.apidevportalmanager.services.ApiMetadataService;
import io.devportal.entities.ApiMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static io.api.apidevportalmanager.resources.util.ResponseBodyWrapper.*;

@RestController
@RequestMapping("/api/v1/metadata")
@RequiredArgsConstructor
public class ApiMetadataResource {

    private final ApiMetadataService apiMetadataService;

    @GetMapping("/stream")
    public ResponseEntity<SseEmitter> testStream() {
        return streamWrap(apiMetadataService.findAll());
    }

    @GetMapping(headers = "x-accept-stream")
    public ResponseEntity<SseEmitter> streamApiMetadata(
            @RequestHeader(value = "x-accept-stream", required = true) Boolean acceptStream) {
        assert acceptStream.equals(Boolean.TRUE);
        return streamWrap(apiMetadataService.findAll());
    }

    @GetMapping
    public ResponseEntity<Iterable<ApiMetadata>> listApiMetadata() {
        return collectionWrap(apiMetadataService.findAll());
    }

    @GetMapping("/{apiMetadataId}")
    public ResponseEntity<ApiMetadata> apiMetadataById(@PathVariable("apiMetadataId") String apiMetadataId) {
        return maybeWrap(apiMetadataService.findById(apiMetadataId));
    }


    @PostMapping
    public SseEmitter publishApiMetadata(@RequestBody ApiMetadata apiMetadata) {
        return streamWrap(apiMetadataService.publishApi(apiMetadata));
    }

    @DeleteMapping("/{apiMetadataId}")
    public ResponseEntity deleteApiMetadata(@PathVariable("apiMetadataId") String apiMetadataId) {
        apiMetadataService.deleteById(apiMetadataId);
        return ResponseEntity.accepted().build();
    }
}
