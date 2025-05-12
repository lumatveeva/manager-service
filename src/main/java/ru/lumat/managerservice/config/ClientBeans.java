package ru.lumat.managerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import ru.lumat.managerservice.clients.ProductsRestClientImpl;

@Configuration
public class ClientBeans {

    @Bean
    public ProductsRestClientImpl productsRestClient(
            @Value("${storeApp.services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            @Value("${storeApp.services.catalogue.security.username}") String catalogueUsername,
            @Value("${storeApp.services.catalogue.security.password}") String cataloguePassword) {
        return new ProductsRestClientImpl(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))
                .build());
    }


}
