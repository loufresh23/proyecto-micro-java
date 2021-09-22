package com.lascencion.mstransaction.handler;

import com.lascencion.mstransaction.domain.dto.TransactionDto;
import com.lascencion.mstransaction.service.inv.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TransactionHandler {

    @Autowired
    private TransactionService service;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), TransactionDto.class);
    }

}
