package com.its.RxVsRsocket.controller;

import com.its.RxVsRsocket.entity.CardEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiConsumer;

@RestController
@Slf4j
public class CardClientController {

    private final RSocketRequester rSocket;

    private WebClient.Builder webClientBuilder;
    private final CardEntity fallBackCard = CardEntity
                                                .builder()
                                                .address("Dwarka")
                                                .cardNumber("535289756842")
                                                .country("India")
                                                .cvv("123")
                                                .expiryDate("1121")
                                                .issuingNetwork("Mastercard")
                                                .name("Krishna")
                                                .build();


    public CardClientController(WebClient.Builder webClientBuilder, @Autowired RSocketRequester.Builder rSocReqBuilder) {
        log.info("Instantiating WebClientBuilder within constructor of CardClientController ");
        this.webClientBuilder = webClientBuilder;
        this.rSocket = rSocReqBuilder
                        .connectTcp("localhost", 2131)
                        .block();


    }

    @GetMapping("/rx/cards")
    //public Mono allWithRx() {
    public Flux <CardEntity> allWithRx() {
        log.info("Entering CardClientController : allWithRx");
        /*Flux cardFlux = webClientBuilder
                            .build()
                            .get()
                            .uri("http://localhost:8091/rx/cards")
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToFlux(CardEntity.class);*/
        Flux <CardEntity> cardFlux = webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8091/rx/cards")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CardEntity.class);

        log.info("Returning cardFlux obtained from downstream card-service application");
        return cardFlux;
        /*return cardFlux
                .collectList()
                .onErrorContinue(new BiConsumer<Throwable, Object>() {
                    @Override
                    public void accept(Throwable throwable, Object o) {
                        log.error("Exception occured while collecting cards from flux - message : {} ", throwable.getMessage());
                    }
                });*/
                //.onErrorReturn(Collections.singletonList(fallBackCard));
    }

    @GetMapping("/rx/card/{cardId}")
    public Mono<CardEntity> cardByIdWithRx(@PathVariable String cardId) {
        log.info("Entering CardClientController : cardByIdWithRx with path variable as {} ", cardId);

         Mono<CardEntity> cardEntityMono = webClientBuilder
                                            /*.filters(exchangeFilterFunctions -> {
                                                exchangeFilterFunctions.add(logRequest());
                                            })
                                            .exchangeFunction(new ExchangeFunction() {
                                                @Override
                                                public Mono<ClientResponse> exchange(ClientRequest request) {
                                                    return null;
                                                }
                                            })*/
                                            .build()
                                            .get()
                                            .uri("http://127.0.0.1:8091/rx/card/{cardId}", cardId)
                                            //.uri("http://rx-vs-rsocket-server/rx/card/{cardId}", cardId)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .retrieve()
                                            .bodyToMono(CardEntity.class)
                                             .onErrorContinue(new BiConsumer<Throwable, Object>() {
                                                 @Override
                                                 public void accept(Throwable throwable, Object o) {
                                                     log.error("Exception occured while fetching card from mono - message : {} ", throwable.getMessage());
                                                 }
                                             });

        log.info("Fetched cardentity mono ");

        return cardEntityMono;
    }

    /*private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
           //clientRequest.
        });
    }*/

    @GetMapping("/rsocket/cards")
    public Flux<CardEntity> fetchAllCardsWithRSocket() {
        log.info("Entering CardClientController : fetchAllCardsWithRSocket");
        Flux<CardEntity> cardFlux = rSocket
                                        .route("rsocket-fetch-all")
                                        .retrieveFlux(CardEntity.class);
        log.info("Flux of all cards obtained from downstream card-service application via RSocket");
        return cardFlux;
    }

    @GetMapping("/rsocket/card/{cardId}")
    public Mono<CardEntity> cardByIdWithRSocket(@PathVariable String cardId) {
        log.info("Entering CardClientController : cardByIdWithRSocket with path variable as {} ", cardId);
        Mono<CardEntity> cardEntityMono = rSocket
                                            .route("rsocket-find-specific")
                                            .data(cardId)
                                            .retrieveMono(CardEntity.class);

        log.info("Card details received");
        return cardEntityMono;
    }
}
