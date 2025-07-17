package org.payment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.payment.dto.ExecutorErrorDto;
import org.payment.exception.IntegrationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Component
public class RestTemplateErrorResponseHandler implements ResponseErrorHandler {
    private final ObjectMapper mapper;

    public RestTemplateErrorResponseHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is5xxServerError();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        ResponseErrorHandler.super.handleError(url, method, response);
        if (response.getStatusCode().is5xxServerError()) {
            ExecutorErrorDto executorErrorDto = mapper.readValue(response.getBody(), ExecutorErrorDto.class);
            throw new IntegrationException("произошла ошибка при вызове внешнего сервиса", executorErrorDto.message());
        }
    }
}
