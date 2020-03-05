package ru.mk.wsa.adapter.endpoint.impl;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;
import ru.mk.wsa.adapter.endpoint.AbstractWsaEndpoint;
import ru.mk.wsa.adapter.model.consts.SearchCustomerDossierDocumentConst;
import ru.mk.wsa.adapter.service.ReceiveResponseWsaService;
import ru.mk.wsa.adapter.service.impl.searchcustomdossierdocument.SearchCustomerDossierDocumentSendRequestWsaService;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;

@Endpoint
public class SearchCustomerDossierDocumentEndpoint extends AbstractWsaEndpoint< JAXBElement<SearchCustomerDossierDocumentReqType>
        , JAXBElement<SearchCustomerDossierDocumentRespType>
        , SearchCustomerDossierDocumentSendRequestWsaService
        , ReceiveResponseWsaService<JAXBElement<SearchCustomerDossierDocumentRespType>>> {

    public SearchCustomerDossierDocumentEndpoint(SearchCustomerDossierDocumentSendRequestWsaService sender, ReceiveResponseWsaService<JAXBElement<SearchCustomerDossierDocumentRespType>> receiver) {
        super(sender, receiver);
    }

    @Override
    @PayloadRoot(namespace = SearchCustomerDossierDocumentConst.NS, localPart = SearchCustomerDossierDocumentConst.SEARCH_CUSTOMER_DOSSIER_DOCUMENT_REQ)
    @ResponsePayload
    public JAXBElement<SearchCustomerDossierDocumentRespType> getSyncRequest(@RequestPayload JAXBElement<SearchCustomerDossierDocumentReqType> req) {
        return super.getSyncRequest2(req);
    }

    @Override
    @PayloadRoot(namespace = SearchCustomerDossierDocumentConst.NS, localPart = SearchCustomerDossierDocumentConst.SEARCH_CUSTOMER_DOSSIER_DOCUMENT_RESP)
    @ResponsePayload
    public void getAsyncResponse(@RequestPayload JAXBElement<SearchCustomerDossierDocumentRespType> resp
            , @SoapHeader(SearchCustomerDossierDocumentConst.RELATES_TO) SoapHeaderElement relatedTo) {
        super.getAsyncResponse2(resp, relatedTo);
    }

}
