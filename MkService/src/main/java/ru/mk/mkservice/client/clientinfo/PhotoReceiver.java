package ru.mk.mkservice.client.clientinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.mk.wsa.adapter587.sync.DocumentReqType;
import ru.mk.wsa.adapter587.sync.DsftSessionType;
import ru.mk.wsa.adapter587.sync.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.sync.SearchCustomerDossierDocumentRespType;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.UUID;

@RequiredArgsConstructor
public class PhotoReceiver extends WebServiceGatewaySupport {

    private final static String NS = "http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1";
    private final static String LP = "SearchCustomerDossierDocumentReq";

//    @Value("photoStore.url")
    private final String photoUrl;

    public String getPhotoUrl(String sessionId, long mdmId) {
        SearchCustomerDossierDocumentReqType req = new SearchCustomerDossierDocumentReqType();
        req.setDsftSessionInfo(new DsftSessionType() {{
            setProcessId(UUID.randomUUID().toString());
            setSessionId(sessionId);
        }});
        req.setDocument(new DocumentReqType() {{
            setDocType("DOCTYPE");
            setDocSubType("DocSubType");
            setDocNumber("123");
        }});
        req.setClientID(Long.valueOf(mdmId).toString());
        JAXBElement<SearchCustomerDossierDocumentReqType> jbReq = new JAXBElement<>(new QName(NS, LP), SearchCustomerDossierDocumentReqType.class, req);
        JAXBElement<SearchCustomerDossierDocumentRespType> jbResp = (JAXBElement<SearchCustomerDossierDocumentRespType>) getWebServiceTemplate().marshalSendAndReceive(jbReq);
        if (jbResp == null
                || jbResp.getValue() == null
                || jbResp.getValue().getRuntimeFaultMSG() == null
                || !"0".equals(jbResp.getValue().getRuntimeFaultMSG().getErrorCode())) {
            throw new RuntimeException("Ошибка вызова сервиса фотографии");
        } else if( jbResp.getValue().getDocument() == null
                || jbResp.getValue().getDocument().getDocLink().isEmpty()) {
            return null;
        }
        return ('/' == photoUrl.charAt(photoUrl.length()-1)?photoUrl:photoUrl+"/") + jbResp.getValue().getDocument().getDocLink();
    }
}
