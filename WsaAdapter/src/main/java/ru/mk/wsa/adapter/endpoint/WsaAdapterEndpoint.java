package ru.mk.wsa.adapter.endpoint;

import lombok.extern.log4j.Log4j2;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;
import ru.mk.wsa.adapter.model.RelatesTo;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Endpoint
@Log4j2
public class WsaAdapterEndpoint {

    @PayloadRoot(namespace = "http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1", localPart = "SearchCustomerDossierDocumentResp")
    @ResponsePayload
    public void searchCustomerDossierDocument(@RequestPayload SearchCustomerDossierDocumentRespType resp
                                              , @SoapHeader("{http://www.w3.org/2005/08/addressing}RelatesTo") SoapHeaderElement relatedTo
    ) {
        log.info("MKresp: OK");
        log.info("RelatesTo - " + (unmarshal(relatedTo, RelatesTo.class)).getValue());
        log.info(resp.getDocument().getDocType());
        log.info(resp.getDocument().getDocSubType());
        log.info(resp.getDocument().getDocComment());
    }

    private <T> T unmarshal(SoapHeaderElement messageID, Class<T> clazz) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(messageID.getSource());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return t;
    }
}
