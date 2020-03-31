package ru.mk.mkservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.mk.mkservice.client.clientinfo.DBInfoReceiver;

@Configuration
public class MkServiceConfig {

    @Bean
    public DBInfoReceiver getClientInfo(@Value("${clientInfo.url}") String url) {
        DBInfoReceiver clientInfo = new DBInfoReceiver();
        clientInfo.setDefaultUri(url);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.mk.common.clientInfo");
        clientInfo.setMarshaller(marshaller);
        clientInfo.setUnmarshaller(marshaller);

        return clientInfo;
    }
}
