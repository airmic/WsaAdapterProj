package ru.mk.wsa.adapter.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.mk.wsa.adapter.config.WsaPropertiesList;
import ru.mk.wsa.adapter.repository.AbstractWsaRepository;

@Repository
public class SearchCustomerDossierDocumentRepository extends AbstractWsaRepository {

    public SearchCustomerDossierDocumentRepository(RedisTemplate<String,Object> redisTemplate
            , @Value("SearchCustomerDossierDocument") String serverName, WsaPropertiesList wsaPropertiesList
    ) {
        super(redisTemplate, serverName, wsaPropertiesList);
    }

}
