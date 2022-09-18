package com.ironhack.vbnk_transactionservice.services.impl;

import com.ironhack.vbnk_transactionservice.data.TransactionType;
import com.ironhack.vbnk_transactionservice.data.http.request.AFRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.AFResponse;
import com.ironhack.vbnk_transactionservice.data.http.responses.ConfirmationResult;
import com.ironhack.vbnk_transactionservice.data.dto.TransactionDTO;
import com.ironhack.vbnk_transactionservice.data.http.request.TransferRequest;
import com.ironhack.vbnk_transactionservice.data.http.responses.DataTransferResponse;
import com.ironhack.vbnk_transactionservice.data.http.responses.TransferResponse;
import com.ironhack.vbnk_transactionservice.repositories.TransactionRepository;
import com.ironhack.vbnk_transactionservice.services.TransactionService;
import com.ironhack.vbnk_transactionservice.utils.VBError;
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

import static com.ironhack.vbnk_transactionservice.data.TransactionState.*;
import static com.ironhack.vbnk_transactionservice.data.TransactionState.OK;
import static com.ironhack.vbnk_transactionservice.data.TransactionType.CHARGE;
import static com.ironhack.vbnk_transactionservice.data.TransactionType.INCOME;
import static com.ironhack.vbnk_transactionservice.utils.VBError.*;
import static org.springframework.http.HttpStatus.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private WebClient dataClient;
    private WebClient antiFraudClient;
    private static final String[] DATA_SERVICE = new String[]{"vbnk-data-service","/v1/data/client/test/ping"};
    private static final String[] ANTI_FRAUD_SERVICE = new String[]{"vbnk-anti-fraud-service","/v1/af/client/test/ping"};
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    TransactionRepository repository;



    @Override
    public HttpResponse<TransactionDTO> getTransaction(String id) {
        return null;
    }


    @Override
    public void delete(String id) {

    }


//-----------------------------------------------------------------------------------------------------INITIATE REQUESTS
    @Override
    public ResponseEntity<TransferResponse> initiateTransferRequest(TransferRequest request, Authentication authentication) throws ServiceUnavailableException {
        checkClientAvailable(DATA_SERVICE, dataClient);
        checkClientAvailable(ANTI_FRAUD_SERVICE,antiFraudClient);
        RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) authentication.getCredentials();
        AccessToken accessToken = context.getToken();
        System.out.println(accessToken.toString());
        var afResponse= antiFraudClient.post()
                .uri("/v1/af/client/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", accessToken.toString())
                .body( Mono.just(request), AFRequest.class)
                .retrieve().bodyToMono(AFResponse.class)
                .block();
        if(afResponse!=null && afResponse.isAllValidated()) {
            if(afResponse.isAllOK()) {
                var res = dataClient.post()
                        .uri("/v1/data/client/tf/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", accessToken.toString())
                        .body(Mono.just(request), TransferRequest.class)
                        .retrieve().bodyToMono(DataTransferResponse.class)
                        .block();

                //COMPLETE TRANSFER: CHARGE + INCOME
                //RECEIVE TRANSFER: INCOME
                //BLIND TRANSFER: CHARGE
                createTransactionsFromTransfer(res);
                return ResponseEntity.ok(new TransferResponse().setState(OK));
            }
            return ResponseEntity.status(CONFLICT).body(fraudFail(afResponse.getFraudLevel()));
        }
        return ResponseEntity.status(BAD_REQUEST).body(new TransferResponse().setState(NOK).addErrors(FATAL_ERROR));
    }

    private void createTransactionsFromTransfer(DataTransferResponse res) {
        if(res !=null){
            if(res.isSource()){
                if(res.isEnoughFounds()&& res.isSrcAccountAvailable()){
                    createTransaction(res.getRequest(),INCOME);
                }
            }
            if(res.isDestination()){
                if (res.isDstAccountAvailable()) {
                    createTransaction(res.getRequest(),CHARGE);
                }
            }
        }
    }

    private TransactionDTO createTransaction(TransferRequest request, TransactionType type) {
        // TODO: 18/09/2022

        //COMPLETE PAYMENT_ORDER: PAYMENT_ORDER(owner) + NOTIFICATION(dest)
        //RECEIVE PAYMENT_ORDER: PAYMENT ORDER(3rd party) + NOTIFICATION(dest)
        //BLIND PAYMENT_ORDER: PAYMENT_ORDER(owner) + NOTIFICATION



        //repository.save();
        return null;
    }

    @Override
    public HttpResponse<TransferResponse> initiatePaymentRequest(TransferRequest request) {
        // TODO: 18/09/2022
        return null;
    }
    @Override
    public HttpResponse<TransactionDTO> updatePendingTransaction(ConfirmationResult result) {
        // TODO: 18/09/2022
        return null;
    }
    @Override
    public TransferResponse receiveBlindTransfer(TransferRequest request) {
        return null;
    }
//-------------------------------------------------------------------------------------------------------PRIVATE METHODS
    private TransferResponse fraudFail(int fraudLevel) {
        var res= new TransferResponse().setState(NOK);
        VBError err = null;
        switch (fraudLevel){
            case (3):{
                err= AF_3;
                //TODO freeze Account
            }
            case (2):{
                if(err==null)err= AF_2;
                //TODO notify admin
            }
            case (1):{
                if(err==null)err= AF_1;
                res.addErrors(err);

                //TODO return response
            }
            default:{
                return res;
            }
        }
    }



//---------------------------------------------------------------------------------------------------------CLIENT CONFIG
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
    private WebClient  createClient(String service) throws ServiceUnavailableException{
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
