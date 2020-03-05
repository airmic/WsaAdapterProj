package ru.mk.wsa.adapter.service.impl.searchcustomdossierdocument;

import org.springframework.stereotype.Service;
import ru.mk.wsa.adapter.service.AbstractTimeoutBreaker;
import ru.mk.wsa.adapter.service.AsyncRunnerService;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class SearchCustomerDossierDocumentTimeoutBreakerService
        extends AbstractTimeoutBreaker<JAXBElement<SearchCustomerDossierDocumentReqType>
            , JAXBElement<SearchCustomerDossierDocumentRespType>
            , AsyncRunnerService<JAXBElement<SearchCustomerDossierDocumentReqType>, JAXBElement<SearchCustomerDossierDocumentRespType>> > {

    public SearchCustomerDossierDocumentTimeoutBreakerService(AsyncRunnerService<JAXBElement<SearchCustomerDossierDocumentReqType>, JAXBElement<SearchCustomerDossierDocumentRespType>> asyncRunnerService, ScheduledThreadPoolExecutor timeoutBreaker) {
        super(asyncRunnerService, timeoutBreaker);
    }
}
