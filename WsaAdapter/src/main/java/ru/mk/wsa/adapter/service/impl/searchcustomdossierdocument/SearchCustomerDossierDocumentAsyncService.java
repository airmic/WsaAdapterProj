package ru.mk.wsa.adapter.service.impl.searchcustomdossierdocument;

import org.springframework.stereotype.Service;
import ru.mk.wsa.adapter.repository.WsaRepository;
import ru.mk.wsa.adapter.sender.ClientSender;
import ru.mk.wsa.adapter.service.AbstractAsyncRunnerService;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;

@Service
public class SearchCustomerDossierDocumentAsyncService extends AbstractAsyncRunnerService<JAXBElement<SearchCustomerDossierDocumentReqType>, JAXBElement<SearchCustomerDossierDocumentRespType>> {
    public SearchCustomerDossierDocumentAsyncService(WsaRepository wsaRepository, ClientSender<JAXBElement<SearchCustomerDossierDocumentReqType>> sender) {
        super(wsaRepository, sender);
    }
}
