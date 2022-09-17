package com.ironhack.vbnk_transactionservice.services.impl;

import com.ironhack.vbnk_transactionservice.data.dto.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionResult;
import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private WebClient client;
    private static final String TARGET_SERVICE = "vbnk-data-service";
    @Autowired
    DiscoveryClient discoveryClient;
    @Override
    public TransactionResult receiveBlindTransfer(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult receivePaymentOrder(TransactionRequest request) {
        return null;
    }

    @Override
    public void updatePendingTransactionTP(ConfirmationResult result) {

    }

    @Override
    public TransactionResult executeTransfer(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executePaymentOrder(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executeInterests(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executeBankCharge(TransactionRequest request) {
        return null;
    }

    @Override
    public HttpResponse<TransactionDTO> getTransaction(String id) {
        return null;
    }

    @Override
    public HttpResponse<TransactionDTO> updatePendingTransaction(ConfirmationResult result) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public HttpResponse<List<TransactionDTO>> getTransactionsByDate(Instant date) {
        return null;
    }

    @Override
    public HttpResponse<List<TransactionDTO>> getTransactionsByDate(String userId, Instant date) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResult> initiatePaymentRequest(Principal sender, TransactionRequest request) {
        return null;
    }

    @Override
    public HttpResponse<TransactionResult> initiateTransferRequest(Principal sender, TransactionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<TransferResponse> initiateTransferRequest(TransactionRequest request, Authentication authentication) throws ServiceUnavailableException {
        checkClientAvailable();
        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) authentication.getCredentials();
        AccessToken accessToken = context.getToken();
        System.out.println(accessToken.toString());
        return ResponseEntity.ok(client.post()
                .uri("/v1/data/client/tf/send")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", accessToken.toString())
                .body( Mono.just(request), TransferRequest.class)
                .retrieve().bodyToMono(TransferResponse.class)
                .block());
    }

    @Override
    public HttpResponse<TransactionResult> initiatePaymentRequest(TransactionRequest request) {
        return null;
    }

    private void checkClientAvailable() throws ServiceUnavailableException {
        try {
            try {
                if (client == null) createClient();
                if (client.get()
                        .uri("/v1/data/client/test/ping")
                        .retrieve()
                        .bodyToMono(String.class)
                        .block()
                        != "pong") createClient();
            } catch (Exception e) {
                createClient();
            }
        }catch (Throwable err){
            if(err instanceof ServiceUnavailableException)throw err;
        }
    }

    void createClient() throws ServiceUnavailableException{
        for (int i = 0; i < 3; i++) {
            try {
                var serviceInstanceList = discoveryClient.getInstances(TARGET_SERVICE);
                String clientURI = serviceInstanceList.get(0).getUri().toString();
                client = WebClient.create(clientURI);
                return;
            } catch (Throwable ignored) {}
            try {Thread.sleep(5000);} catch (InterruptedException ignored) {}
        }
        throw new ServiceUnavailableException();
    }

}
