package com.its.RxVsRsocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
//@Component
public class RSocketClientHandler {
    /*@MessageMapping("client-status")
    public Mono<String> statusUpdate(String status) {
        log.info("# Received ( " + status + " ) at " + LocalDateTime.now());
        return Mono
                .just("confirmed")
                .delayElement(Duration.ofSeconds(1));
    }*/
}
