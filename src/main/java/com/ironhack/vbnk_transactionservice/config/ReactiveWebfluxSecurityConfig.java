//package com.ironhack.vbnk_transactionservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
//import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
//import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@EnableWebFluxSecurity
//@Configuration
//public class ReactiveWebfluxSecurityConfig {
//    //---------------------------------------------------------------------------
//    @Bean
//    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations,
//                        ServerOAuth2AuthorizedClientRepository authorizedClients) {
//        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
//                new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrations, authorizedClients);
//        // (optional) explicitly opt into using the oauth2Login to provide an access token implicitly
//        // oauth.setDefaultOAuth2AuthorizedClient(true);
//        // (optional) set a default ClientRegistration.registrationId
//        // oauth.setDefaultClientRegistrationId("client-registration-id");
//        return WebClient.builder()
//                .filter(oauth)
//                .build();
//    }
//    //---------------------------------------------------------------------------
//    @Bean // with spring-boot-starter-web
//    WebClient webClient(
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientService authorizedClientService
//    ) {
//        var oauth = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
//                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
//                        clientRegistrationRepository, authorizedClientService
//                )
//        );
//        oauth.setDefaultClientRegistrationId("AuthProvider");
//        return WebClient.builder()
//                .apply(oauth.oauth2Configuration())
//                .build();
//    }
//    //---------------------------------------------------------------------------
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .csrf(csrf -> csrf.disable());
//        return http.build();
//    }
//
//}