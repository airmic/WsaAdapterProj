package ru.mk.wsa.adapter.service.impl.searchcustomdossierdocument;

import org.springframework.stereotype.Service;
import ru.mk.wsa.adapter.service.AbstractSendRequestWsaService;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;

@Service
public class SearchCustomerDossierDocumentSendRequestWsaService extends AbstractSendRequestWsaService<JAXBElement<SearchCustomerDossierDocumentReqType>, JAXBElement<SearchCustomerDossierDocumentRespType>> {
    public SearchCustomerDossierDocumentSendRequestWsaService(SearchCustomerDossierDocumentTimeoutBreakerService timeoutBreakerService) {
        super(timeoutBreakerService);
    }
}
