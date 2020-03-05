package ru.mk.wsa.adapter.sender.http.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mk.wsa.adapter.config.WsaPropertiesList;
import ru.mk.wsa.adapter.sender.http.AbstractHttpSender;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;

import javax.xml.bind.JAXBElement;

@Component
public class SearchCustomerDossierDocumentHttpSender extends AbstractHttpSender<JAXBElement<SearchCustomerDossierDocumentReqType>> {

    @Autowired
    SearchCustomerDossierDocumentHttpSender(@Value("SearchCustomerDossierDocument") String uri, WsaPropertiesList wsaPropertiesList) {
        super(uri, wsaPropertiesList);
    }
}
