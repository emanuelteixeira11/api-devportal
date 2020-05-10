package io.api.apidevportalmanager.resources.util;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Objects;

@Slf4j
public abstract class ResponseBodyWrapper<T> {

    public static <T> SseEmitter streamWrap(Single<T> single) {
        return streamWrap(single.toFlowable()).getBody();
    }

    public static <T> SseEmitter streamWrap(Maybe<T> maybe) {
        return streamWrap(maybe.toFlowable()).getBody();
    }

    public static <T> SseEmitter streamWrap(Observable<T> observable) {
        SseEmitter emitter = new SseEmitter();
        observable.subscribe(emitter::send, emitter::completeWithError, emitter::complete);
        return emitter;
    }

    public static <T> ResponseEntity<SseEmitter> streamWrap(Flowable<T> flowable) {
        SseEmitter emitter = new SseEmitter();
        flowable.subscribe(emitter::send, emitter::completeWithError, emitter::complete);
        return ResponseEntity.ok(emitter);
    }

    public static <T> ResponseEntity<T> singleWrap(Single<T> single) {
        return ResponseEntity.ok(single.blockingGet());
    }

    public static <T> ResponseEntity<T> maybeWrap(Maybe<T> maybe) {
        T result = maybe.blockingGet();
        return Objects.isNull(result) ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    public static <T> ResponseEntity<Iterable<T>> collectionWrap(Observable<T> observable) {
        return ResponseEntity.ok(observable.blockingIterable());
    }

    public static <T> ResponseEntity<Iterable<T>> collectionWrap(Flowable<T> flowable) {
        return ResponseEntity.ok(flowable.blockingIterable());
    }
}
