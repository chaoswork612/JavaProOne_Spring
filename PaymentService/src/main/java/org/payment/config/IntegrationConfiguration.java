package org.payment.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(IntegrationClientsProperties.class)
public class IntegrationConfiguration {

    @Bean
    public RestTemplate productsClient(IntegrationClientsProperties integrationClientsProperties) {
        RestTemplateProperties productsClientProperties = integrationClientsProperties.getProductsClient();
        return new RestTemplateBuilder()
                .rootUri(productsClientProperties.getUrl())
                .connectTimeout(productsClientProperties.getConnectTimeout())
                .readTimeout(productsClientProperties.getReadTimeout())
                .build();
    }
}