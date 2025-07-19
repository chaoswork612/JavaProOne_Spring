package org.payment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "integration.clients")
public class IntegrationClientsProperties {
    private RestTemplateProperties productsClient;
}
