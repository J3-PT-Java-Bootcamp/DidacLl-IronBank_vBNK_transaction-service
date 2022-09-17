package com.ironhack.vbnk_transactionservice.services.impl;

import com.ironhack.vbnk_transactionservice.data.http.request.FraudValidationRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.http.request.TransactionRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransactionResult;
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

    private WebClient dataClient;
    private WebClient antiFraudClient;
    private static final String[] DATA_SERVICE = new String[]{"vbnk-data-service","/v1/data/client/test/ping"};
    private static final String[] ANTI_FRAUD_SERVICE = new String[]{"vbnk-anti-fraud-service","/v1/af/client/test/ping"};
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
        checkClientAvailable(DATA_SERVICE, dataClient);
        checkClientAvailable(ANTI_FRAUD_SERVICE,antiFraudClient);
        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) authentication.getCredentials();
        AccessToken accessToken = context.getToken();
        System.out.println(accessToken.toString());
        var fraud= dataClient.post()
                .uri("/v1/af/client/tf/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", accessToken.toString())
                .body( Mono.just(request), FraudValidationRequest.class)
                .retrieve().bodyToMono(TransferResponse.class)
                .block();
        var res= ResponseEntity.ok(dataClient.post()
                .uri("/v1/data/client/tf/send")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", accessToken.toString())
                .body( Mono.just(request), TransferRequest.class)
                .retrieve().bodyToMono(TransferResponse.class)
                .block());
        return res;
    }

    @Override
    public HttpResponse<TransactionResult> initiatePaymentRequest(TransactionRequest request) {
        return null;
    }

    @Override
    public TransactionResult executeCharge(TransactionRequest request) {
        return null;
    }

    private WebClient checkClientAvailable(String[] service,WebClient webClient) throws ServiceUnavailableException {
        try {
            try {
                if (webClient == null) createClient(service[0]);
                if (webClient.get()
                        .uri(service[1])
                        .retrieve()
                        .bodyToMono(String.class)
                        .block()
                        != "pong")return createClient(service[0]);
            } catch (Exception e) {
                return createClient(service[0]);
            }
        }catch (Throwable err){
            if(err instanceof ServiceUnavailableException)throw err;
        }
        return webClient;
    }

    WebClient  createClient(String service) throws ServiceUnavailableException{
        for (int i = 0; i < 3; i++) {
            try {
                var serviceInstanceList = discoveryClient.getInstances(service);
                String clientURI = serviceInstanceList.get(0).getUri().toString();
                return WebClient.create(clientURI);
            } catch (Throwable ignored) {}
            try {Thread.sleep(5000);} catch (InterruptedException ignored) {}
        }
        throw new ServiceUnavailableException();
    }

}
