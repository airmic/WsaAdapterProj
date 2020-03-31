package ru.mk.wsa.server.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import ru.mk.wsa.adapter587.DocumentRespType;
import ru.mk.wsa.adapter587.ErrorMessageType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentReqType;
import ru.mk.wsa.adapter587.SearchCustomerDossierDocumentRespType;
import ru.mk.wsa.server.param.SearchCustomDossierDocumentRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Log4j2
public class WsaClient extends WebServiceGatewaySupport {

    private final String WSA_URI = "http://www.w3.org/2005/08/addressing";
    private final String RESP_ACTION = "http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1/SearchCustomerDossierDocumentCallBack";

    public void sendSearchCustomerDossierDocumentResp (SearchCustomDossierDocumentRequest request) throws URISyntaxException, InterruptedException {
        SearchCustomerDossierDocumentRespType resp = getResp(request.getReq());

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.mk.wsa.adapter587");
        setMarshaller( marshaller);
        setUnmarshaller(marshaller);
        QName respQName = new QName("http://www.vtb24.ru/ApplicationConnectorServiceLibrary/DSFT/Requesters/SearchCustomerDossierDocumentDSFTReqA/V1","SearchCustomerDossierDocumentResp");
        JAXBElement<SearchCustomerDossierDocumentRespType> restJb = new JAXBElement(respQName, SearchCustomerDossierDocumentRespType.class, resp);
//        TimeUnit.SECONDS.sleep(20);
        sendWithHeadersVersion(request, restJb);
    }


    private void sendWithHeadersVersion(SearchCustomDossierDocumentRequest request, JAXBElement<SearchCustomerDossierDocumentRespType> restJb) {
        getWebServiceTemplate().marshalSendAndReceive(request.getReplyTo().getValue(), restJb, new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                SoapHeader header = ((SoapMessage)message).getSoapHeader();
                header.addNamespaceDeclaration("wsa", WSA_URI);
                header.addHeaderElement(new QName(WSA_URI,"MessageID","wsa"))
                        .setText("uuid:"+UUID.randomUUID().toString());
                header.addHeaderElement(new QName(WSA_URI,"RelatesTo","wsa"))
                        .setText(request.getMessageID().getValue());
                header.addHeaderElement(new QName(WSA_URI,"Action","wsa"))
                        .setText(RESP_ACTION);
            }
        });
    }

    private SearchCustomerDossierDocumentRespType getResp(SearchCustomerDossierDocumentReqType req) {
        SearchCustomerDossierDocumentRespType resp = new SearchCustomerDossierDocumentRespType();
        resp.setRuntimeFaultMSG( new ErrorMessageType() {{
            setErrorCode("0");
        }});
        resp.setDocument(
                new DocumentRespType() {{
                    if(req.getDocument() != null) {
                        if( req.getDocument().getDocType() != null )
                            setDocType(req.getDocument().getDocType());
                        if( req.getDocument().getDocSubType() != null )
                            setDocSubType(req.getDocument().getDocSubType());
                        setDocComment("Comment");
                        setDocID("55555");
                        setDocLink("GetImage.aspx?FileID=123-UUID-1&TTL=88898-32k2-44");

                    }
                }}
        );
        return resp;
    }


}
