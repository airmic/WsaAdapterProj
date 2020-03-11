package ru.mk.wsa.adapter.service.impl.searchcustomdossierdocument;

import org.springframework.stereotype.Component;
import ru.mk.wsa.adapter.repository.WsaRepository;
import ru.mk.wsa.adapter.service.AbstractReceiveResultWsaService;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;

@Component
public class SearchCustomerDossierDocumentReceiveResultWsaService extends AbstractReceiveResultWsaService<JAXBElement<SearchCustomerDossierDocumentRespType>> {

    public SearchCustomerDossierDocumentReceiveResultWsaService(WsaRepository wsaRepository) {
        super(wsaRepository);
    }

}
