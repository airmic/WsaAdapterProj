package ru.mk.wsa.tmp;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.mk.wsa.adapter587.DocumentRespType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class MyClient extends WebServiceGatewaySupport {

    public void sendMessgeToAdapter587() {
        SearchCustomerDossierDocumentRespType req = new SearchCustomerDossierDocumentRespType();
        req.setDocument(new DocumentRespType() {{
            setDocType("DocType");
            setDocSubType("DocSubType");
            setDocComment("DocComment");
        }});

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.mk.wsa.adapter587");
        setMarshaller( marshaller);
        setUnmarshaller(marshaller);


//        QName reqQname = new QName()
        QName reqQName = new QName("http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1","SearchCustomerDossierDocumentResp");
        JAXBElement<SearchCustomerDossierDocumentRespType> jaxbReq = new JAXBElement<>(reqQName, SearchCustomerDossierDocumentRespType.class, req);
        getWebServiceTemplate().marshalSendAndReceive("http://localhost:8092/wsa/adapter587", jaxbReq);
    }
}
