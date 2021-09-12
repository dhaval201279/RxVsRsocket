package com.its.RxVsRsocket.config;

import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketConnector;
import io.rsocket.metadata.WellKnownMimeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketConnectorConfigurer;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class RSocketClientConfig {
    /*@Bean
    public RSocket rSocket(@Value("${spring.rsocket.server.port}") Integer serverPort) {
        return RSocketFactory
                .connect()
                .mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create(serverPort))
                .start()
                .block();
    }

    @Bean
    RSocketRequester rSocketRequester(RSocket rSocket, RSocketStrategies rSocketStrategies) {
        return RSocketRequester
                .wrap(rSocket, MimeTypeUtils.APPLICATION_JSON, MimeTypeUtils.APPLICATION_JSON,
                    rSocketStrategies);
    }*/

   /* @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder rSocketReqBuilder,
                                                RSocketStrategies rSocketStrategies,
                                                @Value("${spring.rsocket.server.port}") Integer serverPort) {


        log.info("3 Entering RSocketClientConfig : rSocketRequester with spring.rsocket.server.port : {}", serverPort);

        SocketAcceptor responder = RSocketMessageHandler.responder(rSocketStrategies, new RSocketClientHandler());

        return rSocketReqBuilder
                .setupRoute("connect")
                .setupData("user")
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .metadataMimeType(MimeType.valueOf(WellKnownMimeType.MESSAGE_RSOCKET_COMPOSITE_METADATA.getString()))
                *//*.rsocketStrategies(builder ->
                        builder.encoder())*//*
                .rsocketConnector(connector -> connector.acceptor(responder))
                .connectTcp("127.0.0.1", serverPort)
                .block();

        *//*return rSocketReqBuilder
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .rsocketFactory(RSocketMessageHandler.clientResponder(rSocketStrategies, new RSocketClientHandler()))
                .setupRoute("connect")
                .setupData("user")
                .connectTcp("127.0.0.1", serverPort)
                .block();*//*
    }*/
}
