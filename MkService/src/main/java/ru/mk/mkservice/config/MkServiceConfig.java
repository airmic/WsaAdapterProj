package ru.mk.mkservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.mk.mkservice.client.clientinfo.DBInfoReceiver;
import ru.mk.mkservice.client.clientinfo.PhotoReceiver;

@Configuration
public class MkServiceConfig {

    @Bean
    public DBInfoReceiver getDbInfoReceiver(@Value("${dbInfoReceiver.url}") String url) {
        DBInfoReceiver dbInfoReceiver = new DBInfoReceiver();
        dbInfoReceiver.setDefaultUri(url);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.mk.common.clientInfo");
        dbInfoReceiver.setMarshaller(marshaller);
        dbInfoReceiver.setUnmarshaller(marshaller);

        return dbInfoReceiver;
    }

    @Bean
    public PhotoReceiver getPhotoReceiver(@Value("${photoReceiver.url}") String photoReceiverUrl
            , @Value("${photoStore.url}") String photoStoreUrl) {
        PhotoReceiver photoReceiver = new PhotoReceiver(photoStoreUrl);
        photoReceiver.setDefaultUri(photoReceiverUrl);
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.mk.wsa.adapter587.sync");
        photoReceiver.setMarshaller(marshaller);
        photoReceiver.setUnmarshaller(marshaller);
        return photoReceiver;
    }
}
