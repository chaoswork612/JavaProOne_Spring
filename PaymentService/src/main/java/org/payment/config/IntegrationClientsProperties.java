package org.payment.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "integration.clients")
public class IntegrationClientsProperties {
    private final RestTemplateProperties productsClient;

    @ConstructorBinding
    public IntegrationClientsProperties(RestTemplateProperties productsClient) {
        this.productsClient = productsClient;
    }

}
