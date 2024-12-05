package ru.artemiyandstepan.bridge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.artemiyandstepan.bridge.service.SoapClientService;

@Configuration
public class SoapClientConfig {

    @Value("${soap.service.url}")
    private String soapServiceUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.soap"); // Пакет с сгенерированными SOAP-классами
        return marshaller;
    }

    @Bean
    public SoapClientService soapClientService(Jaxb2Marshaller marshaller) {
        SoapClientService client = new SoapClientService();
        client.setDefaultUri(soapServiceUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
