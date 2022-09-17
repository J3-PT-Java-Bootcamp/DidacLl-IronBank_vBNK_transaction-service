package com.ironhack.vbnk_transactionservice.repositories.impl;

import com.ironhack.vbnk_transactionservice.data.dto.DataValidationRequest;
import com.ironhack.vbnk_transactionservice.data.dto.DataValidationResponse;
import com.ironhack.vbnk_transactionservice.repositories.DataRepository;
import com.ironhack.vbnk_transactionservice.utils.Money;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Repository
public class DataRepositoryVBNK implements DataRepository {

    private static final String TARGET_SERVICE = "vbnk-data-service";
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;
    @Value("${spring.application.name}")
    private String applicationName;
    private WebClient client;

    public String ping(String ping) {
//        System.out.println(token);
        return client.get()
                .uri("/v1/data/client/" + ping)
//                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
//
//    @Override
//    public VBUserDTO getUser(String id) {
//        var res = client.get()
//                .uri("/v1/data/client/users")
//                .retrieve()
//                .bodyToMono(VBUserDTO.class)
//                .block();
//        return res;
//    }
//
//    @Override
//    public AccountDTO getAccount(String ref) {
//        return client.get()
//                .uri("/v1/data/auth/users")
//                .retrieve()
//                .bodyToMono(AccountDTO.class)
//                .block();
//    }
//
//    @Override
//    public List<AccountHolderDTO> getOwners(String accountRef) {
//        return null;
//    }

    @Override
    public DataValidationResponse validateAccount(DataValidationRequest request) {
        return null;
    }

    @Override
    public boolean applyCharge(String accountRef, Money amount) {
        return false;
    }

    @Override
    public boolean applyIncome(String accountRef, Money amount) {
        return false;
    }

    @SneakyThrows
    @PostConstruct
    void createClient() {
        var serviceInstanceList = discoveryClient.getInstances(TARGET_SERVICE);
        try {
            String clientURI = serviceInstanceList.get(0).getUri().toString();
            client = WebClient.create(clientURI);
        } catch (Exception e) {
            //FIXME probably will crash when other services need this workaround
            Thread.sleep(1000);
            createClient();
        }
    }
}
