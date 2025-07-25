package org.payment.config;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class RestTemplateProperties {
    private String url;
    private Duration connectTimeout;
    private Duration readTimeout;
}
